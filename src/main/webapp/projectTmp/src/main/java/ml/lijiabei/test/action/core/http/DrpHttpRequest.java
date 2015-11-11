package ml.lijiabei.templete.action.core.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 封装了一些http request常用的方法和转换, TODO 还可以添加上上传文件的处理
 * 
 * @author stevenblue
 * 
 */
public interface DrpHttpRequest {
	HttpServletRequest getRequest();

	String getParameter(String p);

	String getParameter(String p, String def);

	long getParameter(String p, long def);

	float getParameter(String p, float def);

	int getParameter(String p, int def);

	boolean getParameter(String p, boolean def);

	String[] getParameterValues(String p);

	void setAttribute(String name, Object o);

	HttpSession getSession();

	HttpSession getSession(boolean flag);
}