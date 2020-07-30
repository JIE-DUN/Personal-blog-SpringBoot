package com.li.blog.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.li.blog.entity.User;
import com.li.blog.service.UserService;
import com.li.blog.util.Consts;

/**
 * shiro的Realm
 *
 */
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取授权信息
	 * 只有一个用户，就不获取权限信息了
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	
	/**
	 * 登录验证
	 * token:令牌，基于用户名和密码的令牌
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		User u = new User();
		u.setUsername(upToken.getUsername());
		User user = userService.getByEntity(u);
		if(user !=null){
			User principal = user;
			Object hashedCredentials = user.getPassword();
			SecurityUtils.getSubject().getSession().setAttribute(Consts.USER,user);
			AuthenticationInfo authenInfo = new SimpleAuthenticationInfo(principal, hashedCredentials, getName());
			return authenInfo;
		}
		return null;
	}

}
