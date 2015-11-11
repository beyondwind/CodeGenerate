package com.sbkj.codegenerate.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sbkj.codegenerate.bo.ControllerResult;
import com.sbkj.codegenerate.bo.GenerateInfo;
import com.sbkj.codegenerate.bo.ProjectFreemarkBO;
import com.sbkj.codegenerate.domain.CodeColumn;
import com.sbkj.codegenerate.domain.CodeTable;
import com.sbkj.codegenerate.error.DBException;
import com.sbkj.codegenerate.error.ProcessException;
import com.sbkj.codegenerate.error.TableModuleException;
import com.sbkj.codegenerate.module.GenerateProcess;
import com.sbkj.codegenerate.module.TableModule;
import com.sbkj.codegenerate.utils.FileUtil;
import com.sbkj.codegenerate.utils.FileZip;
import com.sbkj.codegenerate.utils.GenerateConstants;
import com.sbkj.codegenerate.utils.StringUtill;
import com.sbkj.codegenerate.utils.ValidateUtils;

@Controller
public class CommonController {

	protected static Logger logger = Logger.getLogger(CommonController.class.getName());

	@RequestMapping(value = "indexPage.htm")
	public String indexPage(HttpServletRequest request) {
		return "indexPage";
	}

	@RequestMapping(value = "propertySettingPage.htm")
	public String propertySettingPage(HttpServletRequest request) {
		ControllerResult controllerResult = new ControllerResult();
		
		try {
			Object generateInfo = request.getSession().getAttribute("generateInfo");
			if (null != generateInfo) {
				request.setAttribute("generateInfo", generateInfo);
			}
		} catch (Exception e) {
			controllerResult.setSuccess(false);
			controllerResult.setMsg("系统异常！");
			logger.error("propertySettingPage系统异常！", e);
		}
		request.setAttribute("result", controllerResult);
		return "step1";
	}

	@RequestMapping(value = "chooseTablePage.htm")
	public String chooseTablePage(HttpServletRequest request, GenerateInfo generateInfo) {
		ControllerResult controllerResult = new ControllerResult();
		try {
			String msg = checkGenerateInfo(generateInfo);
			if (!StringUtill.isEmptyString(msg)) {
				controllerResult.setMsg(msg);
				controllerResult.setSuccess(false);
				request.setAttribute("generateInfo", generateInfo);
				request.setAttribute("result", controllerResult);
				return "step1";
			}

			TableModule tableModule = new TableModule();
			List<CodeTable> codeTableList = tableModule.queryCodeTableList(generateInfo);
			controllerResult.setDataObject(codeTableList);
			request.getSession().setAttribute("generateInfo", generateInfo);
		} catch (DBException e) {
			controllerResult.setSuccess(false);
			controllerResult.setMsg(e.getMessage());
			logger.error("chooseTablePage系统异常！", e);
			request.setAttribute("generateInfo", generateInfo);
			request.setAttribute("result", controllerResult);
			return "step1";
		} catch (TableModuleException e) {
			controllerResult.setSuccess(false);
			controllerResult.setMsg(e.getMessage());
			logger.error("chooseTablePage系统异常！", e);
			request.setAttribute("generateInfo", generateInfo);
			request.setAttribute("result", controllerResult);
			return "step1";
		} catch (Exception e) {
			controllerResult.setSuccess(false);
			controllerResult.setMsg("系统异常！");
			logger.error("chooseTablePage系统异常！", e);
			request.setAttribute("generateInfo", generateInfo);
			request.setAttribute("result", controllerResult);
			return "step1";
		}
		request.setAttribute("result", controllerResult);
		return "step2";
	}

	@RequestMapping(value = "downloadPage.htm")
	public String downloadPage(HttpServletRequest request, String[] choosedTable) {
		ControllerResult controllerResult = new ControllerResult();
		try {
			if (null == choosedTable || choosedTable.length < 1) {
				controllerResult.setSuccess(false);
				controllerResult.setMsg("请选择表！");
				request.setAttribute("result", controllerResult);
				return "step2";
			}
			Object generateInfoObj = request.getSession().getAttribute("generateInfo");
			if (null == generateInfoObj) {
				controllerResult.setSuccess(false);
				controllerResult.setMsg("请填写数据库连接信息！");
				request.setAttribute("result", controllerResult);
				return "step1";
			}
			GenerateInfo generateInfo = (GenerateInfo) generateInfoObj;

			CodeTable codeTable = null;
			List<CodeTable> tableList = new ArrayList<CodeTable>();
			TableModule tableModule = new TableModule();
			for (int i = 0; i < choosedTable.length; i++) {
				codeTable = new CodeTable();
				codeTable.setTableName(choosedTable[i]);
				List<CodeColumn> codeColumnList = tableModule.queryCodeColumnList(generateInfo, choosedTable[i]);

				codeTable.setColumns(codeColumnList);
				tableList.add(codeTable);
			}

			// 模板路径
			String freemarkpath = request.getSession().getServletContext().getRealPath("/freemarker");
			// 工程初始化文件路径
			String projectTmpletePath = request.getSession().getServletContext().getRealPath("/projectTmp");
			// 用户工程临时路径
			String userPath = request.getSession().getServletContext().getRealPath("/userTemp") + File.separator + request.getSession().getId();

			ProjectFreemarkBO projectFreemarkBO = new ProjectFreemarkBO();
			projectFreemarkBO.setFreemarkpath(freemarkpath);
			projectFreemarkBO.setPackagePath(generateInfo.getPackagePath());
			projectFreemarkBO.setProjectTmpletePath(projectTmpletePath);
			projectFreemarkBO.setUserPath(userPath);

			// 生产流程对象
			GenerateProcess generateProcess = new GenerateProcess();
			// 将表、列原数据加工下
			if (!generateProcess.polishTableList(tableList, generateInfo.getDatabaseType())) {
				controllerResult.setSuccess(false);
				controllerResult.setMsg("表数据加工异常！");
				request.setAttribute("result", controllerResult);
				return "step2";
			}
			// 初始化用户目录工程文件夹
			generateProcess.createTargetDir(projectFreemarkBO);
			// 生成文件
			generateProcess.doMainProcess(projectFreemarkBO, tableList);

			File sourceFile = new File(projectFreemarkBO.getUserProjectPath());
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(projectFreemarkBO.getUserProjectPathZip()));
			FileZip.zip(out, sourceFile, null);
			out.close();

			controllerResult.setDataObject(tableList);
		} catch (ProcessException e) {
			controllerResult.setSuccess(false);
			controllerResult.setMsg("主流程处理异常！");
			logger.error("主流程处理异常！", e);
		} catch (Exception e) {
			controllerResult.setSuccess(false);
			controllerResult.setMsg("系统异常！");
			logger.error("系统异常！", e);
		}
		request.setAttribute("result", controllerResult);
		return "step3";
	}

	@RequestMapping(value = "/download.htm")
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String userZipPath = request.getSession().getServletContext().getRealPath("/userTemp") + File.separator + request.getSession().getId();
		ProjectFreemarkBO projectFreemarkBO = new ProjectFreemarkBO();
		projectFreemarkBO.setUserPath(userZipPath);

		try {
			File zipFile = new File(projectFreemarkBO.getUserProjectPathZip());
			if (!zipFile.exists()) {
				return null;
			}
			long fileLength = zipFile.length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=project.zip");
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(projectFreemarkBO.getUserProjectPathZip()));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

			File userFileDir = new File(projectFreemarkBO.getUserPath());

			// 删除临时文件
			if (userFileDir.exists()) FileUtil.delFolder(projectFreemarkBO.getUserPath());

		} catch (Exception e) {
			logger.error("下载异常！", e);
		} finally {
			if (bis != null) bis.close();
			if (bos != null) bos.close();
		}
		return null;
	}

	private String checkGenerateInfo(GenerateInfo generateInfo) {
		String msg = "";
		try {
			// ip校验
			if (StringUtill.isEmptyString(generateInfo.getIpAddress())) {
				msg += "<br/>ip地址不能为空！ ";
			} else if (!ValidateUtils.checkDIY(generateInfo.getIpAddress(), "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$")) {
				msg += "<br/>ip地址格式不正确！  ";
			}

			// port校验
			if (StringUtill.isEmptyString(generateInfo.getPort())) {
				msg += "<br/>数据库端口不能为空！  ";
			} else if (!ValidateUtils.isDiaget(generateInfo.getPort())) {
				msg += "<br/>数据库端口为1~65536的数字！  ";
			} else if (generateInfo.getIntegerPort() < 1 || generateInfo.getIntegerPort() > 65536) {
				msg += "<br/>数据库端口为1~65536的数字！  ";
			}

			// 数据库用户名校验
			if (StringUtill.isEmptyString(generateInfo.getUserName())) {
				msg += "<br/>数据库用户名不能为空！  ";
			}

			if (generateInfo.getDatabaseType() != GenerateConstants.TYPE_MYSQL) {
				msg += "<br/>目前只支持MySQL！  ";
			}

			// 数据库校验
			if (StringUtill.isEmptyString(generateInfo.getDbName())) {
				msg += "<br/>数据库名不能为空！  ";
			}

			// Package Path校验
			if (StringUtill.isEmptyString(generateInfo.getPackagePath())) {
				msg += "<br/>包路径不能为空！  ";
			}

		} catch (Exception e) {
			return "<br/>校验异常！";
		}
		return msg;
	}

}
