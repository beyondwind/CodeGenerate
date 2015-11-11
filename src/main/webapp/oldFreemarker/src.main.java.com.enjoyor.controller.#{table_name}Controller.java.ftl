package ${package}.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enjoyor.common.Page;
import com.enjoyor.common.util.CommonUtil;
import com.enjoyor.common.util.Webconstant;
import com.enjoyor.pojo.${table_name};
import com.enjoyor.service.${table_name}Service;

import com.enjoyor.pojo.${table_name};
import com.enjoyor.dao.${table_name}Dao;
import com.enjoyor.service.${table_name}Service;

@Controller
public class ${table_name}Controller {

	@Autowired
	private ${table_name}Service service;

	@RequestMapping(value = "/${table_name}/indexPage.htm")
	public String indexPage(Model model, HttpServletRequest request) {
		return "${table_name}";
	}
}