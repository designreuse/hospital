package com.dpc.utils.memcached;

import java.util.Map;

public class MemSession {
	// 会话ID
	private String sid = "";
	// 存放本会话的所有信息
	private Map map = null;

	public static MemSession getSession(String sid,String expireType) {
		MemSession session = null;
		session = new MemSession(sid, true,expireType);
		return session;
	}

	public static MemSession getSession(String sid, boolean create,String expireType) {
		MemSession session = null;

		session = new MemSession(sid, create,expireType);

		return session;
	}
	
	public static void removeSession(String sid) {
		
		SessionService.getInstance().removeSession(sid);

	}

	private MemSession(String sid, boolean create,String expireType) {
		this.sid = sid;
		this.map = SessionService.getInstance().getSession(sid, create,expireType);
	}

	public static boolean sessionExists(String sid) {
		return SessionService.getInstance().sessionExists(sid);
	}

	public Object getAttribute(String key) {
		return this.map.get(key);
	}

	public void invalidate() {
		this.map.clear();
		SessionService.getInstance().removeSession(this.sid);
	}

	public void removeAttribute(String key,String expireType) {
		if (key == null || key.trim().length() <= 0) {
			return;
		}
		this.map.remove(key);
		SessionService.getInstance().saveSession(this.sid, this.map,expireType);
	}

	public void setAttribute(String key, Object value,String expireType) {
		if (key == null || key.trim().length() <= 0 || value == null) {
			return;
		}
		this.map.put(key, value);
		SessionService.getInstance().saveSession(this.sid, this.map,expireType);
	}

	public void updateExpiryDate(String sid,String expireType) {
		SessionService.getInstance().updateExpiryDate(sid,expireType);
	}

	public Map getMap() {
		return map;
	}

	public static void removeAllSession() {
		SessionService.getInstance().flushAll();		
	}

}
