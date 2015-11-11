package com.enjoyor.common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.enjoyor.common.util.CustomSessionContext;

/**
 * @author : wxq
 * @date : 2012-12-3 下午6:11:10 监听session创建与销毁，将session放入缓存中
 * 
 **/
public class CustomSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		CustomSessionContext.getInstance().AddSession(se.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		CustomSessionContext.getInstance().DelSession(se.getSession());
	}

}
