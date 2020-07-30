package com.li.blog.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.li.blog.realm.UserRealm;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {
	
	/**
	 * shiroFilter
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//登录路径
		shiroFilterFactoryBean.setLoginUrl("/admin");
		//权限不足时跳转到的页面，由于该项目只有一个权限，所以不配置
		//shiroFilterFactoryBean.setUnauthorizedUrl("");
		
		Map filterChainMap = new LinkedHashMap<String,String>();
		//只有后台界面需要登录
		filterChainMap.put("/admin/login", "anon");
		filterChainMap.put("/admin/**", "authc");
		filterChainMap.put("/**", "anon");
		//权限、角色格式
//		filterChainMap.put("/admin/**", "perms[a,b,c]");
//		filterChainMap.put("/admin/**", "roles[a,b,c]");
		
		//确认具体网址访问权限
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 安全管理器
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//放入firstRealm()
		securityManager.setRealm(firstRealm());
		//放入sessionManager()，使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}
	
	/**
	 * Realm
	 */
	@Bean
	public Realm firstRealm(){
		UserRealm userRealm = new UserRealm();
		//加密方式
//		userRealm.setCredentialsMatcher(credentialsMatcher());
		return userRealm;
	}
	
//	/**
//	 * 设置Realm里的加密方式及次数等配置
//	 */
//	@Bean
//	public CredentialsMatcher credentialsMatcher(){
//		HashedCredentialsMatcher hashedMatcher  = new HashedCredentialsMatcher();
//		//加密方式
//		hashedMatcher.setHashAlgorithmName("md5");
//		//加密次数
//		hashedMatcher.setHashIterations(1);
//		return hashedMatcher;
//	}
	
	/**
	 * 使用注解方式控制每个路径需要的权限
	 */
//	@Bean
//	@ConditionalOnMissingBean
//	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
//		DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
//		defaultAAP.setProxyTargetClass(true);
//		return null;
//	}
	
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}
//	
	/**
	 * 会话管理器
	 * redis
	 */
	@Bean
	public SessionManager sessionManager(){
		DefaultWebSessionManager ssionManager = new DefaultWebSessionManager();
		ssionManager.setSessionDAO(redisSessionDao());
		
		//设置会话过期时间,半小时
		ssionManager.setGlobalSessionTimeout(3 * 60 * 1000);
		//默认调用SessionDAO的Delete方法删除会话
		ssionManager.setDeleteInvalidSessions(true);
		//设置会话定期检查，这里是默认一个小时
//		ssionManager.setSessionValidationInterval(3 * 60 * 1000);
//		ssionManager.setSessionValidationSchedulerEnabled(true);
		return ssionManager;
	}
	
	/**
	 * 注册自定义的ShiroRedisSessionDao
	 */
	@Bean
	public SessionDAO redisSessionDao(){
		ShiroRedisSessionDao redisDAO = new ShiroRedisSessionDao();
		return redisDAO;
	}
}
















