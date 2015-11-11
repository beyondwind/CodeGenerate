package ${package}.action;

import javax.servlet.http.HttpServletRequest;

import ${package}.bo.Query${table_name}BO;
import ${package}.bo.result.BaseResultDO;
import ${package}.bo.result.ResultDO;
import ${package}.bo.result.ResultList;
import ${package}.domain.${table_name};
import ${package}.service.${table_name}Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/${table_name?uncap_first}")
public class ${table_name}Action {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ${table_name}Service ${table_name?uncap_first}Service;

	@RequestMapping
	public String index(HttpServletRequest request) {
		ResultList<${table_name}> resultList = null;
		try {
			Query${table_name}BO query${table_name}BO = new Query${table_name}BO();
			resultList = ${table_name?uncap_first}Service.query${table_name}List(query${table_name}BO);
		} catch (Exception e) {
			resultList = new ResultList<${table_name}>();
			resultList.setSuccess(false);
			resultList.setMsg("${table_name}Action index error");
			log.error("${table_name}Action index error", e);
		}
		if (!resultList.isSuccess()) {
			request.setAttribute("msg", resultList.getMsg());
		}
		request.setAttribute("${table_name?uncap_first}List", resultList.getResult());
		return "${table_name}/indexPage";
	}

	@RequestMapping(value = "createPage")
	public String createPage(HttpServletRequest request) {
		return "${table_name}/createPage";
	}

	@RequestMapping(value = "modifyPage")
	public String modifyPage(HttpServletRequest request, @RequestParam int id) {
		ResultDO<${table_name}> resultDO = null;
		try {
			Query${table_name}BO query${table_name}BO = new Query${table_name}BO();
			query${table_name}BO.setId(id);
			resultDO = ${table_name?uncap_first}Service.query${table_name}ById(query${table_name}BO);
		} catch (Exception e) {
			resultDO = new ResultDO<${table_name}>();
			resultDO.setSuccess(false);
			resultDO.setMsg("${table_name}Action modifyPage error");
			log.error("${table_name}Action modifyPage error", e);
		}
		if (!resultDO.isSuccess()) {
			request.setAttribute("msg", resultDO.getMsg());
		}
		request.setAttribute("${table_name?uncap_first}", resultDO.getResult());
		return "${table_name}/modifyPage";
	}

	@RequestMapping(value = "create")
	public String create(HttpServletRequest request,
			@ModelAttribute("${table_name?uncap_first}") ${table_name} ${table_name?uncap_first},
			RedirectAttributes redirectAttributes) {
		BaseResultDO baseResultDO = null;
		try {
			baseResultDO = ${table_name?uncap_first}Service.create${table_name}(${table_name?uncap_first});
		} catch (Exception e) {
			baseResultDO = new BaseResultDO();
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("${table_name}Action create error");
			log.error("${table_name}Action create error", e);
		}
		if (!baseResultDO.isSuccess()) {
			request.setAttribute("msg", baseResultDO.getMsg());
			request.setAttribute("${table_name?uncap_first}", ${table_name?uncap_first});
			return "${table_name}/createPage";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/");

		redirectAttributes.addFlashAttribute("msg", "新增成功！");
		return "redirect:" + sb.toString() + "${table_name?uncap_first}.htm";
	}

	@RequestMapping(value = "modify")
	public String modify(HttpServletRequest request,
			@ModelAttribute("${table_name?uncap_first}") ${table_name} ${table_name?uncap_first},
			RedirectAttributes redirectAttributes) {
		BaseResultDO baseResultDO = null;
		try {
			baseResultDO = ${table_name?uncap_first}Service.modify${table_name}(${table_name?uncap_first});
		} catch (Exception e) {
			baseResultDO = new BaseResultDO();
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("${table_name}Action modify error");
			log.error("${table_name}Action modify error", e);
		}
		if (!baseResultDO.isSuccess()) {
			request.setAttribute("msg", baseResultDO.getMsg());
			request.setAttribute("${table_name?uncap_first}", ${table_name?uncap_first});
			return "${table_name}/modifyPage";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/");

		redirectAttributes.addFlashAttribute("msg", "修改成功！");
		return "redirect:" + sb.toString() + "${table_name?uncap_first}.htm";
	}

	@RequestMapping(value = "remove")
	public String remove(HttpServletRequest request, @RequestParam int id,
			RedirectAttributes redirectAttributes) {
		BaseResultDO baseResultDO = null;
		try {
			${table_name} ${table_name?uncap_first} = new ${table_name}();
			${table_name?uncap_first}.setId(id);
			baseResultDO = ${table_name?uncap_first}Service.remove${table_name}(${table_name?uncap_first});
		} catch (Exception e) {
			baseResultDO = new BaseResultDO();
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("${table_name}Action remove error");
			log.error("${table_name}Action remove error", e);
		}
		if (!baseResultDO.isSuccess()) {
			redirectAttributes.addFlashAttribute("msg", baseResultDO.getMsg());
		}

		StringBuffer sb = new StringBuffer();
		sb.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/");

		redirectAttributes.addFlashAttribute("msg", "删除成功！");
		return "redirect:" + sb.toString() + "${table_name?uncap_first}.htm";
	}
}