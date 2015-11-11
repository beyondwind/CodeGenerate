package ml.lijiabei.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ml.lijiabei.entity.CodeColumn;
import ml.lijiabei.entity.CodeTable;
import ml.lijiabei.service.CodeTableService;
import ml.lijiabei.utill.FileUtil;
import ml.lijiabei.utill.MySqlType;
import ml.lijiabei.utill.OracleType;
import ml.lijiabei.utill.ResourcesProperties;
import ml.lijiabei.utill.StringUtill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Controller
public class MainController extends BaseController<CodeTable> {

	@Autowired
	private CodeTableService codeTableService;

	private Map<String, String> resProperties;

	public Map<String, String> getMapProperties() {
		if (this.resProperties == null || this.resProperties.isEmpty()) {
			resProperties = ResourcesProperties.getInstance().getResourcesPropreties();
		}
		return resProperties;
	}

	@RequestMapping(value = "indexPage.htm")
	public String indexPage(HttpServletRequest request) {
		getMapProperties();
		return "indexPage";
	}

	@RequestMapping(value = "baseSetPage.htm")
	public String baseSetPage(HttpServletRequest request) {
		getMapProperties();
		String project_path = resProperties.get("project_path");
		String package_name = resProperties.get("package_name");
		String modual_name = resProperties.get("modual_name");
		String databse_type = resProperties.get("databse_type");
		String database_owner = resProperties.get("database_owner");
		request.getSession().setAttribute("project_path", project_path);
		request.getSession().setAttribute("package_name", package_name);
		request.getSession().setAttribute("modual_name", modual_name);
		request.getSession().setAttribute("databse_type", databse_type);
		request.getSession().setAttribute("database_owner", database_owner);
		return "baseSet";
	}

	@RequestMapping(value = "additionSetPage.htm")
	public String additionSetPage(HttpServletRequest request) {
		return "baseSet";
	}

	@RequestMapping(value = "chooseTablePage.htm")
	public String chooseTablePage(HttpServletRequest request, String project_path, String package_name, String modual_name, String databse_type, String database_owner) {
		request.getSession().setAttribute("project_path", project_path);
		request.getSession().setAttribute("package_name", package_name);
		request.getSession().setAttribute("modual_name", modual_name);
		request.getSession().setAttribute("databse_type", databse_type);
		request.getSession().setAttribute("database_owner", database_owner);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("owner", request.getSession().getAttribute("database_owner"));
		map.put("type", request.getSession().getAttribute("databse_type"));
		List<CodeTable> tableList = codeTableService.getList(map);
		Map<String, CodeTable> tableMap = new HashMap<String, CodeTable>();
		for (CodeTable codeTable : tableList) {
			tableMap.put(codeTable.getTable_name(), codeTable);
		}
		request.getSession().setAttribute("tableMap", tableMap);
		request.setAttribute("tableList", tableList);
		return "chooseTable";
	}

	@RequestMapping(value = "finish.htm")
	public String finish(HttpServletRequest request, String[] selectedTables) {
		String project_path = (String) request.getSession().getAttribute("project_path");// 工程保存路径
		if (!project_path.endsWith(File.separator)) project_path += File.separator;
		String projectTmpPath = request.getSession().getServletContext().getRealPath("/projectTmp");// 工程初始化文件路径
		// 模板路径
		String freemarkpath = request.getSession().getServletContext().getRealPath("/freemarker");
		File freemarkerTemps = new File(freemarkpath);
		String package_name = (String) request.getSession().getAttribute("package_name");// 包名称
		String package_path = "";// 包主体最深路径
		String databse_type = (String) request.getSession().getAttribute("databse_type");
		package_path = initDir(project_path, projectTmpPath, package_name);
		log.debug("package_path:" + package_path);

		// 生成选择的菜单列表
		CodeTable temp = null;
		@SuppressWarnings("unchecked")
		Map<String, CodeTable> tableMap = (Map<String, CodeTable>) request.getSession().getAttribute("tableMap");
		List<CodeTable> choosedTableList = new ArrayList<CodeTable>();
		if (selectedTables != null) for (int i = 0; i < selectedTables.length; i++) {
			log.debug(selectedTables[i]);
			temp = tableMap.get(selectedTables[i]);
			choosedTableList.add(temp);
		}

		Configuration cfg = new Configuration();
		// 加载freemarker模板文件
		try {
			cfg.setDirectoryForTemplateLoading(new File(freemarkpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 设置对象包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		// 设计异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

		File[] templetes = freemarkerTemps.listFiles();
		for (File file : templetes) {
			// 原始的模板名称
			String templeteName = file.getName();
			log.debug("current templete:" + templeteName);

			String templeteDest = templeteName;
			// 替换模板文件包路径
			if (templeteDest.indexOf("#{package_path}") > 0) {
				templeteDest = templeteDest.replace("#{package_path}", package_name);
			}

			for (CodeTable codeTable : choosedTableList) {
				// 修饰后的表名称
				String tableName = StringUtill.modifyTableName(codeTable.getTable_name());
				templeteDest = templeteDest.replace("#{table_name}", tableName);
				List<CodeColumn> tColumns = JdbcToJava(codeTable.getColumns(), databse_type);
				String primaryKey = "";
				String primaryType = "";
				String jPrimaryKey = "";
				for (CodeColumn codeColumn : tColumns) {
					if ("1".equals(codeColumn.getIs_primary())) {
						primaryKey = codeColumn.getColumn_name();
						primaryType = codeColumn.getData_type();
					}
				}
				if ("".equals(primaryKey)) {
					// 当没有主键情况下，第一列默许为主键
					primaryKey = tColumns.get(0).getColumn_name();
					primaryType = tColumns.get(0).getData_type();
				}
				jPrimaryKey = StringUtill.modifyColumnName(primaryKey);
				String templeteDestPath = "";
				String[] tempNamePaths = templeteDest.split("\\.");
				// 模板名称.的间隔小于3，不能进行正常解析
				if (tempNamePaths.length < 3) {
					break;
				}
				// 文件后缀不为ftl，非正确模板
				if (!"ftl".equals(tempNamePaths[tempNamePaths.length - 1])) {
					break;
				}
				// 模板名称.的间隔大于3，创建模板所在文件夹
				if (tempNamePaths.length > 3) {
					String[] tempPath = new String[tempNamePaths.length - 3];
					for (int i = 0; i < tempNamePaths.length - 3; i++)
						tempPath[i] = tempNamePaths[i];
					// 在项目路径下创建模板对应的文件夹路径
					templeteDestPath = FileUtil.createDirByPackge(tempPath, project_path);
				}
				// ftl前的两个字符串组成目标文件名称
				String templeteDestName = tempNamePaths[tempNamePaths.length - 3] + "." + tempNamePaths[tempNamePaths.length - 2];
				// 最终模板需要转换的目标文件
				File templeteDestFile = new File(templeteDestPath + templeteDestName);

				// 定义并设置数据
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("package", package_name);
				data.put("raw_table", codeTable.getTable_name());
				data.put("table_name", tableName);
				data.put("table_columns", tColumns);
				data.put("primary_key", primaryKey);
				data.put("primary_type", primaryType);
				data.put("j_primary_key", jPrimaryKey);

				Template template = null;
				// 获取指定模板文件
				try {
					template = cfg.getTemplate(templeteName);
					// 定义输入文件，默认生成在工程根目录
					Writer out = new OutputStreamWriter(new FileOutputStream(templeteDestFile), "UTF-8");
					// 最后开始生成
					template.process(data, out);
				} catch (TemplateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		request.setAttribute("tableList", choosedTableList);
		return "finish";
	}

	@ResponseBody
	@RequestMapping(value = "readfile.htm")
	public String readfile(HttpServletRequest request) {
		// 模板路径
		String freemarkpath = request.getSession().getServletContext().getRealPath("/freemarker");
		File dir = new File(freemarkpath);
		String[] filelist = dir.list();
		for (int i = 0; i < filelist.length; i++) {
			System.out.println(filelist[i]);
		}
		return "{\"state\":\"successful\"}";
	}

	@ResponseBody
	@RequestMapping(value = "test.htm")
	public String testFreemark(HttpServletRequest request) {
		// 模板路径
		String dir = request.getSession().getServletContext().getRealPath("/freemarker");
		Configuration cfg = new Configuration();

		// 加载freemarker模板文件
		try {
			cfg.setDirectoryForTemplateLoading(new File(dir));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 设置对象包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		// 设计异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

		// 定义并设置数据
		Map<String, String> data = new HashMap<String, String>();
		data.put("persion", "michael");
		Template template = null;
		// 获取指定模板文件
		try {
			template = cfg.getTemplate("test.ftl");
			File file = new File("D:\\test.html");
			// 定义输入文件，默认生成在工程根目录
			Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");

			// 最后开始生成
			template.process(data, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{\"state\":\"successful\"}";
	}

	@ResponseBody
	@RequestMapping(value = "menu/getTree.htm")
	public List<CodeTable> getTableList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		return codeTableService.getList(map);
	}

	private String initDir(String project_path, String projectTmpPath, String package_name) {
		String package_path = "";
		File project = new File(project_path);
		if (!project.exists()) project.mkdir();
		// 项目初始文件复制
		FileUtil.copyDirectory(projectTmpPath, project_path, true);
		// 包创建
		String[] package_paths = package_name.split("\\.");
		// 包路径加上项目路径
		package_path = project_path + "src" + File.separator + "main" + File.separator + "java" + File.separator;
		File p_path = null;
		// 包文件夹创建
		for (int i = 0; i < package_paths.length; i++) {
			package_path = package_path + package_paths[i] + File.separator;
			p_path = new File(package_path);
			if (!p_path.exists()) p_path.mkdir();
		}
		return package_path;
	}

	private List<CodeColumn> JdbcToJava(List<CodeColumn> columns, String databaseType) {
		String jdbcType = "";
		String javaType = "";
		if ("oracle".equals(databaseType)) {
			for (CodeColumn codeColumn : columns) {
				jdbcType = codeColumn.getColumn_type().toUpperCase();
				javaType = OracleType.valueOf(jdbcType).getJavaType();
				codeColumn.setData_type(javaType);
				codeColumn.setData_name(StringUtill.modifyColumnName(codeColumn.getColumn_name()));
				log.info(codeColumn.getColumn_name() + " type:" + codeColumn.getData_type() + " is pkey:" + codeColumn.getIs_primary());
			}
		} else if ("mysql".equals(databaseType)) {
			for (CodeColumn codeColumn : columns) {
				jdbcType = codeColumn.getColumn_type().toUpperCase();
				javaType = MySqlType.valueOf(jdbcType).getJavaType();
				codeColumn.setData_type(javaType);
				codeColumn.setData_name(StringUtill.modifyColumnName(codeColumn.getColumn_name()));
				log.info(codeColumn.getColumn_name() + " type:" + codeColumn.getData_type());
			}
		} else return null;
		return columns;
	}
}
