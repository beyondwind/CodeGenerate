package com.enjoyor.common.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * @author : wxq
 * @date : 2012-12-3 下午6:46:58
 *
 **/
public class CustomSessionContext {
	
	private static CustomSessionContext instance;
    private HashMap map;
    private CustomSessionContext() {
        map = new HashMap();
    }
    public static CustomSessionContext getInstance() {
        if (instance == null) {
            instance = new CustomSessionContext();
        }
        return instance;
    }
    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
            map.put(session.getId(), session);
        }
    }
    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
            map.remove(session.getId());
        }
    }
    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) map.get(session_id);
    }

}
