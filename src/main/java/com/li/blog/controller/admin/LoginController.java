package com.li.blog.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.li.blog.util.CryptographyUtil;

/**
 * 用户登录控制器
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    /**
     * 跳转登录页面
     * 返回登录页面
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录校验
     * 登录成功跳转登录成功页面，登录失败返回登录页面
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        RedirectAttributes attributes) {
		String pw = CryptographyUtil.md5Manager(password, username);
		// Subject,shiro权限验证
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, pw);
		try {
			// 传递token给shiro的realm
			subject.login(token);
			return "admin/index";
		} catch (Exception e) {
			// 如果登录失败，则在后台反应信息
			e.printStackTrace();
			attributes.addFlashAttribute("message", "用户名或密码错误");
		}
		return "redirect:/admin";
    }

    /**
     * 注销
     */
    @GetMapping("/logout")
    public String logout() {
    	SecurityUtils.getSubject().logout();
        return "redirect:/admin";
    }

}