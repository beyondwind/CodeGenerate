package ml.lijiabei.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import ml.lijiabei.entity.Page;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

/**
 * Title:基础Controller<br/>
 * Description:提供log4j的记录，空DATE类型处理<br/>
 * 
 * @author lijiabei
 * @version 1.0 创建时间：2013-07-10
 * 
 */
public class BaseController<T> {
	protected static Logger log = Logger.getLogger(BaseController.class
			.getName());

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}
