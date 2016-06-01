package org.mybatis.smvc.controller;

import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.enums.Role;
import org.mybatis.smvc.exception.SmvcException;
import org.mybatis.smvc.service.UserService;
import org.mybatis.smvc.validators.Add;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Amysue on 2016/5/30.
 */
@Controller
@RequestMapping("/user")
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

}
