package com.li.blog.config;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroRedisSessionDao extends CachingSessionDAO {

	public static final String SHIRO_SESSION_KEY = "shiro_session_key";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	protected void doUpdate(Session session) {
		this.saveSession(session);
	}

	@Override
	protected void doDelete(Session session) {
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return ;
		}
		redisTemplate.boundHashOps(SHIRO_SESSION_KEY).delete(session.getId());
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			logger.error("传入的 session id is null");
			return null;
		}
		return (Session) redisTemplate.boundHashOps(SHIRO_SESSION_KEY).get(sessionId);
	}
	
	
	private void saveSession(Session session) {
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return ;
		}
		redisTemplate.boundHashOps(SHIRO_SESSION_KEY).put(session.getId(),session);
	}

}
