package com.li.blog.controller.admin;

import com.li.blog.entity.User;
import com.li.blog.service.UserService;
import com.li.blog.util.CryptographyUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 用户登录控制器
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 跳转登录页面
     * @Auther: ONESTAR
     * @Date: 9:57 2020/3/27
     * @Param:
     * @Return: 返回登录页面
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * @Description: 登录校验
     * @Auther: ONESTAR
     * @Date: 10:04 2020/3/27 
     * @Param: username:用户名
     * @Param: password:密码
     * @Param: session:session域
     * @Param: attributes:返回页面消息
     * @Return: 登录成功跳转登录成功页面，登录失败返回登录页面
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
//        User user = userService.checkUser(username, password);
//        if (user != null) {
//            user.setPassword(null);
//            session.setAttribute("user",user);
//            return "admin/index";
//        } else {
//            attributes.addFlashAttribute("message", "用户名和密码错误");
//            return "redirect:/admin";
//        }

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
     * @Description: 注销
     * @Auther: ONESTAR
     * @Date: 10:15 2020/3/27
     * @Param: session:session域
     * @Return: 返回登录页面
     */
    @GetMapping("/logout")
    public String logout() {
    	SecurityUtils.getSubject().logout();
        return "redirect:/admin";
    }

}