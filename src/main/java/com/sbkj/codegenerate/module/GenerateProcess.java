package com.sbkj.codegenerate.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbkj.codegenerate.bo.ProjectFreemarkBO;
import com.sbkj.codegenerate.domain.CodeColumn;
import com.sbkj.codegenerate.domain.CodeTable;
import com.sbkj.codegenerate.error.ProcessException;
import com.sbkj.codegenerate.utils.FileUtil;
import com.sbkj.codegenerate.utils.GenerateConstants;
import com.sbkj.codegenerate.utils.MySqlType;
import com.sbkj.codegenerate.utils.StringUtill;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class GenerateProcess {

	/**
	 * @Title: polishTableList
	 * @Description:将数据库获取的表、列数据，转化成模板需要的形式
	 * @param codeTableList
	 * @param databaseType
	 * @return boolean 返回类型
	 */
	public boolean polishTableList(List<CodeTable> codeTableList, int databaseType) {
		if (null == codeTableList || databaseType != GenerateConstants.TYPE_MYSQL) {
			return false;
		}
		for (CodeTable codeTable : codeTableList) {

			// 将待下划线的表名改成驼峰命名的存入TableNamePolished中
			codeTable.setTableNamePolished(StringUtill.modifyTableName(codeTable.getTableName()));

			String jdbcType = "";// 对应的列在数据库中的类型
			String javaType = "";// 对应的列在java中的类型
			for (CodeColumn codeColumn : codeTable.getColumns()) {
				jdbcType = codeColumn.getColumnType().toUpperCase();
				javaType = MySqlType.valueOf(jdbcType).getJavaType();
				codeColumn.setDataType(javaType);
				codeColumn.setDataName(StringUtill.modifyColumnName(codeColumn.getColumnName()));

				// 如果是主键，将其提取出放入表对象的主键列属性中
				if ("1".equals(codeColumn.getIsPrimary())) {
					codeTable.setMainColumn(codeColumn);
				}
			}

			// 如果找不到主键，将第一列作为主键
			if (null == codeTable.getMainColumn()) {
				codeTable.setMainColumn(codeTable.getColumns().get(0));
			}
		}

		return true;
	}

	/**
	 * @Title: createTargetDir
	 * @Description: 创建基础的项目文件夹，附带基础的文件
	 * @param projectFreemarkBO
	 * @return 设定文件 boolean 返回类型
	 */
	public void createTargetDir(ProjectFreemarkBO projectFreemarkBO) {
		String package_path = "";
		File project = new File(projectFreemarkBO.getUserProjectPath());
		if (!project.exists()) project.mkdir();
		// 项目初始文件复制
		FileUtil.copyDirectory(projectFreemarkBO.getProjectTmpletePath(), projectFreemarkBO.getUserProjectPath(), true);
		// 包创建
		String[] package_paths = projectFreemarkBO.getPackagePath().split("\\.");
		// 包路径加上项目路径
		package_path = projectFreemarkBO.getUserProjectPath() + "src" + File.separator + "main" + File.separator + "java" + File.separator;
		File p_path = null;
		// 包文件夹创建
		for (int i = 0; i < package_paths.length; i++) {
			package_path = package_path + package_paths[i] + File.separator;
			p_path = new File(package_path);
			if (!p_path.exists()) p_path.mkdir();
		}
	}

	/**
	 * @Title: doMainProcess
	 * @Description: 主流程处理，读取模板，将表内容映射到模板上
	 * @param projectFreemarkBO
	 * @return boolean 返回类型
	 * @throws ProcessException
	 */
	public boolean doMainProcess(ProjectFreemarkBO projectFreemarkBO, List<CodeTable> codeTableList) throws ProcessException {
		Configuration cfg = new Configuration();
		File freemarkerTemps = new File(projectFreemarkBO.getFreemarkpath());
		// 加载freemarker模板文件
		try {
			cfg.setDirectoryForTemplateLoading(new File(projectFreemarkBO.getFreemarkpath()));
		} catch (IOException e) {
			throw new ProcessException("模板加载失败！", e);
		}
		// 设置对象包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		// 设计异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

		boolean processResult = true;
		// 获取文件列表，遍历
		File[] templetes = freemarkerTemps.listFiles();
		for (File file : templetes) {
			for (CodeTable codeTable : codeTableList) {
				String TempleteDestFilePath = getTempleteDestFilePath(file.getName(), codeTable, projectFreemarkBO);
				if (StringUtill.isEmptyString(TempleteDestFilePath)) {
					continue;
				}
				if (!createTempleteDestFile(TempleteDestFilePath, file.getName(), codeTable, projectFreemarkBO, cfg)) {
					processResult = false;
				}
			}
		}

		return processResult;
	}

	/**
	 * @Title: getTempleteDestFilePath
	 * @Description: 获取表对应模板，最终路径
	 * @param freemarkTempleteName
	 * @param codeTable
	 * @param projectFreemarkBO
	 */
	private String getTempleteDestFilePath(String freemarkTempleteName, CodeTable codeTable, ProjectFreemarkBO projectFreemarkBO) {
		String replacedTemplete = freemarkTempleteName;
		// 替换模板文件包路径
		if (replacedTemplete.indexOf("#{package_path}") > 0) {
			replacedTemplete = replacedTemplete.replace("#{package_path}", projectFreemarkBO.getPackagePath());
		}

		replacedTemplete = replacedTemplete.replace("#{table_name}", codeTable.getTableNamePolished());
		String templeteDestPath = "";
		String[] tempNamePaths = replacedTemplete.split("\\.");
		// 模板名称.的间隔小于3，不能进行正常解析
		if (tempNamePaths.length < 3) {
			return null;
		}
		// 文件后缀不为ftl，非正确模板
		if (!"ftl".equals(tempNamePaths[tempNamePaths.length - 1])) {
			return null;
		}
		// 模板名称.的间隔大于3，创建模板所在文件夹
		if (tempNamePaths.length > 3) {
			String[] tempPath = new String[tempNamePaths.length - 3];
			for (int i = 0; i < tempNamePaths.length - 3; i++)
				tempPath[i] = tempNamePaths[i];
			// 在项目路径下创建模板对应的文件夹路径
			templeteDestPath = FileUtil.createDirByPackge(tempPath, projectFreemarkBO.getUserProjectPath());
		}
		// ftl前的两个字符串组成目标文件名称
		String templeteDestName = tempNamePaths[tempNamePaths.length - 3] + "." + tempNamePaths[tempNamePaths.length - 2];
		return templeteDestPath + templeteDestName;
	}

	private boolean createTempleteDestFile(String templeteDestFilePath, String freemarkTempleteName, CodeTable codeTable, ProjectFreemarkBO projectFreemarkBO, Configuration cfg) {
		if (StringUtill.isEmptyString(templeteDestFilePath) || StringUtill.isEmptyString(freemarkTempleteName)) return false;
		// 最终模板需要转换的目标文件
		File templeteDestFile = new File(templeteDestFilePath);

		// 定义并设置数据
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("package", projectFreemarkBO.getPackagePath());
		data.put("raw_table", codeTable.getTableName());
		data.put("table_name", codeTable.getTableNamePolished());
		data.put("table_columns", codeTable.getColumns());
		data.put("primary_key", codeTable.getMainColumn().getColumnName());
		data.put("primary_type", codeTable.getMainColumn().getDataType());
		data.put("j_primary_key", codeTable.getMainColumn().getDataName());

		Template template = null;
		// 获取指定模板文件
		try {
			template = cfg.getTemplate(freemarkTempleteName);
			// 定义输入文件，默认生成在工程根目录
			Writer out = new OutputStreamWriter(new FileOutputStream(templeteDestFile), "UTF-8");
			// 最后开始生成
			template.process(data, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
