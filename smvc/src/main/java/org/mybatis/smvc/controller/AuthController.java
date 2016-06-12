package org.mybatis.smvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.service.UserService;
import org.mybatis.smvc.validators.UpdatePwd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Amysue on 2016/6/12.
 */
@Controller
public class AuthController {
    private UserService userService;
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @Resource(name = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Validated(UpdatePwd.class) User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("error", "用户名或者密码格式不正确");
            return "auth/login";
        }
        Subject               subject = SecurityUtils.getSubject();
        UsernamePasswordToken token   = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("error", "用户名或者密码错误");
            return "auth/login";
        }
        User lguser = userService.loadByUsername(user.getUsername());
        subject.getSession().setAttribute("lguser", lguser);
        SavedRequest req = WebUtils.getSavedRequest(null);
        if (req != null) {
            String url = req.getRequestUrl();
            return "redirect:"+url;
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            SecurityUtils.getSubject().logout();
            return "home";
        } else {
            return "redirect:login";
        }
    }

}
