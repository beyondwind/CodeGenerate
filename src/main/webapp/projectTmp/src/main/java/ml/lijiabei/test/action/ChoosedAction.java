package ml.lijiabei.templete.action;

import javax.servlet.http.HttpServletRequest;

import ml.lijiabei.templete.bo.QueryChoosedBO;
import ml.lijiabei.templete.bo.result.BaseResultDO;
import ml.lijiabei.templete.bo.result.ResultDO;
import ml.lijiabei.templete.bo.result.ResultList;
import ml.lijiabei.templete.domain.Choosed;
import ml.lijiabei.templete.service.ChoosedService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/choosed")
public class ChoosedAction {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ChoosedService choosedService;

	@RequestMapping
	public String index(HttpServletRequest request) {
		ResultList<Choosed> resultList = null;
		try {
			QueryChoosedBO queryChoosedBO = new QueryChoosedBO();
			resultList = choosedService.queryChoosedList(queryChoosedBO);
		} catch (Exception e) {
			resultList = new ResultList<Choosed>();
			resultList.setSuccess(false);
			resultList.setMsg("ChoosedAction index error");
			log.error("ChoosedAction index error", e);
		}
		if (!resultList.isSuccess()) {
			request.setAttribute("msg", resultList.getMsg());
		}
		request.setAttribute("choosedList", resultList.getResult());
		return "choosed/indexPage";
	}

	@RequestMapping(value = "createPage")
	public String createPage(HttpServletRequest request) {
		return "choosed/createPage";
	}

	@RequestMapping(value = "modifyPage")
	public String modifyPage(HttpServletRequest request, @RequestParam int id) {
		ResultDO<Choosed> resultDO = null;
		try {
			QueryChoosedBO queryChoosedBO = new QueryChoosedBO();
			queryChoosedBO.setId(id);
			resultDO = choosedService.queryChoosedById(queryChoosedBO);
		} catch (Exception e) {
			resultDO = new ResultDO<Choosed>();
			resultDO.setSuccess(false);
			resultDO.setMsg("ChoosedAction modifyPage error");
			log.error("ChoosedAction modifyPage error", e);
		}
		if (!resultDO.isSuccess()) {
			request.setAttribute("msg", resultDO.getMsg());
		}
		request.setAttribute("choosed", resultDO.getResult());
		return "choosed/modifyPage";
	}

	@RequestMapping(value = "create")
	public String create(HttpServletRequest request,
			@ModelAttribute("choosed") Choosed choosed,
			RedirectAttributes redirectAttributes) {
		BaseResultDO baseResultDO = null;
		try {
			baseResultDO = choosedService.createChoosed(choosed);
		} catch (Exception e) {
			baseResultDO = new BaseResultDO();
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("ChoosedAction create error");
			log.error("ChoosedAction create error", e);
		}
		if (!baseResultDO.isSuccess()) {
			request.setAttribute("msg", baseResultDO.getMsg());
			request.setAttribute("choosed", choosed);
			return "choosed/createPage";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/");

		redirectAttributes.addFlashAttribute("msg", "新增成功！");
		return "redirect:" + sb.toString() + "choosed.htm";
	}

	@RequestMapping(value = "modify")
	public String modify(HttpServletRequest request,
			@ModelAttribute("choosed") Choosed choosed,
			RedirectAttributes redirectAttributes) {
		BaseResultDO baseResultDO = null;
		try {
			baseResultDO = choosedService.modifyChoosed(choosed);
		} catch (Exception e) {
			baseResultDO = new BaseResultDO();
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("ChoosedAction modify error");
			log.error("ChoosedAction modify error", e);
		}
		if (!baseResultDO.isSuccess()) {
			request.setAttribute("msg", baseResultDO.getMsg());
			request.setAttribute("choosed", choosed);
			return "choosed/modifyPage";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/");

		redirectAttributes.addFlashAttribute("msg", "修改成功！");
		return "redirect:" + sb.toString() + "choosed.htm";
	}

	@RequestMapping(value = "remove")
	public String remove(HttpServletRequest request, @RequestParam int id,
			RedirectAttributes redirectAttributes) {
		BaseResultDO baseResultDO = null;
		try {
			Choosed choosed = new Choosed();
			choosed.setId(id);
			baseResultDO = choosedService.removeChoosed(choosed);
		} catch (Exception e) {
			baseResultDO = new BaseResultDO();
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("ChoosedAction remove error");
			log.error("ChoosedAction remove error", e);
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
		return "redirect:" + sb.toString() + "choosed.htm";
	}
}