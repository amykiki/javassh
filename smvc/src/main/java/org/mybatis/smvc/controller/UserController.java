package org.mybatis.smvc.controller;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.enums.Role;
import org.mybatis.smvc.exception.SmvcException;
import org.mybatis.smvc.service.UserService;
import org.mybatis.smvc.validators.Add;
import org.mybatis.smvc.validators.CheckPassword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * Created by Amysue on 2016/5/30.
 */
@Controller
@RequestMapping("/user")
@Validated
public class UserController {
    private UserService userService;

    @Resource(name = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        model.addAttribute("deps", userService.listDep());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Validated({Add.class}) User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("deps", userService.listDep());
            return "user/add";
        }
        user.setRole(Role.NORMAL);
        try {
            userService.add(user);
        } catch (SmvcException e) {
            String eMsg = e.getMessage();
            if (eMsg.startsWith("dep:")) {
                br.rejectValue("dep", "error.user", eMsg.substring(eMsg.indexOf("dep:") + "dep:".length()));

            } else if (eMsg.startsWith("username:")) {
                br.rejectValue("username", "error.user", eMsg.substring(eMsg.indexOf("username:") + "username:".length()));
            }
            model.addAttribute("deps", userService.listDep());
            return "user/add";
        }
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model) {
        model.addAttribute(userService.load(id));
        return "user/show";
    }

    @RequestMapping(value = "/{id}/pwd", method = RequestMethod.GET)
    public String updatePwd() {
        return "user/pwd";
    }

    @CheckPassword
    @RequestMapping(value = "/{id}/pwd", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String updatePwd(
            @Length(min = 4, max = 10, message = "密码长度必须在4-10之间") @NotBlank(message = "必须输入新密码") @RequestParam("newPwd") String newPwd,
            @NotBlank(message = "必须确认密码") @RequestParam("confirmPwd") String confirmPwd,
            @NotBlank(message = "必须输入原有密码") @RequestParam("oldPwd") String oldPwd,
            @PathVariable int id,
            Model model) {

        try {
            userService.updatePwd(id, oldPwd, newPwd);
        } catch (SmvcException e) {
            model.addAttribute("error", e.getMessage());
            return "user/pwd";
        }
        model.addAttribute(userService.load(id));
        return "redirect:/user/" + id;
    }

}
