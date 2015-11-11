package com.enjoyor.common.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.enjoyor.common.Page;
import com.enjoyor.pojo.SysUser;

/**
 * @author : wxq
 * @date : 2012-8-28 下午4:46:24
 * 
 **/
public class CommonUtil {

	public static Page getPage(HttpServletRequest request) {
		Page page = new Page();
		String currentPage = request.getParameter("pageNo") == null ? ""
				: request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize") == null ? ""
				: request.getParameter("pageSize");
		if (currentPage.compareTo("") == 0) {
			page.setPageNo(1);
		} else {
			page.setPageNo(Integer.valueOf(currentPage));
		}
		if (pageSize.compareTo("") == 0) {
			page.setDefaultPageSize();
		} else {
			page.setPageSize(Integer.valueOf(pageSize));
		}
		return page;
	}

	public static SysUser getSessionUser(HttpServletRequest request) {
		return (SysUser) request.getSession().getAttribute("CURRENTUSER");
	}

	public static HashMap getSessionListMenu(HttpServletRequest request) {
		return (HashMap) request.getSession().getAttribute("mapMenu");
	}

	public static boolean hasMenu(HttpServletRequest request, int menuId) {
		HashMap map = getSessionListMenu(request);
		return map != null && map.containsKey(menuId);
	}
}
