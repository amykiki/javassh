package org.mybatis.smvc.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mybatis.smvc.entity.*;
import org.mybatis.smvc.enums.Role;
import org.mybatis.smvc.exception.SmvcException;
import org.mybatis.smvc.realm.UserRealm;
import org.mybatis.smvc.service.DepService;
import org.mybatis.smvc.service.UserService;
import org.mybatis.smvc.validators.Add;
import org.mybatis.smvc.validators.CheckPassword;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Amysue on 2016/5/30.
 */
@Controller
@RequestMapping("/user")
@Validated
public class UserController {
    private UserService      userService;
    @Resource(name = "depService")
    private DepService       depService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Resource(name = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        model.addAttribute("deps", depService.cacheListDep());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Validated({Add.class}) User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("deps", depService.cacheListDep());
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
            model.addAttribute("deps", depService.cacheListDep());
            return "user/add";
        }
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model) {
        model.addAttribute(userService.load(id));
        return "user/show";
    }

    @RequestMapping(value = "/pwd", method = RequestMethod.GET)
    public String updatePwd() {
        return "user/pwd";
    }

    @CheckPassword
    @RequestMapping(value = "/pwd", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String updatePwd(
            @Length(min = 4, max = 10, message = "密码长度必须在4-10之间") @NotBlank(message = "必须输入新密码") @RequestParam("newPwd") String newPwd,
            @NotBlank(message = "必须确认密码") @RequestParam("confirmPwd") String confirmPwd,
            @NotBlank(message = "必须输入原有密码") @RequestParam("oldPwd") String oldPwd,
            Model model) {
        UserPsw userPsw = (UserPsw) SecurityUtils.getSubject().getPrincipal();
        int id = userPsw.getId();
        try {
            userService.updatePwd(userPsw.getUsername(), oldPwd, newPwd);
        } catch (SmvcException e) {
            model.addAttribute("error", e.getMessage());
            return "user/pwd";
        }
        model.addAttribute(userService.load(id));
        return "redirect:/user/" + id;
    }

    @RequestMapping(value = "/watch", method = RequestMethod.GET)
    public String watch(Model model) {
        logger.info("欢迎来到Watch Controller");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        logger.info("::Server Time = " + sf.format(date));
        model.addAttribute("serverTime", sf.format(date));
        return "user/watch";
    }

    @RequestMapping(value = "/resp1", params = "id", produces = "application/json")
    public void respons1(HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json;utf-8");
        String jsonData = "{\"id\": 100, \"username\": \"amyuse\"}";
        resp.getWriter().write(jsonData);
    }

    @RequestMapping(value = "/view1")
    public ModelAndView view1(Model model) {
        model.addAttribute("a", "a");
        ModelAndView mv = new ModelAndView("user/success");
        mv.addObject("a", "update");
        model.addAttribute("a", "b");
        return mv;
    }

    @RequestMapping(value = "/reqparam1")
    public String reqParam1(@RequestParam("ids") String ids, Model model,
            @RequestHeader("User-Agent") String agent, @RequestHeader("Accept") String[] accepts) {
        System.out.println(ids);
        model.addAttribute("ids", ids);
        return "user/success";
    }

    @RequestMapping(value = "/reqparam2")
    public String reqParam2(@RequestParam("ids") int[] ids) {
        System.out.println(ids.length);
        return "user/success";
    }

    @RequestMapping(value = "/reqparam3")
    public String reqParam3(@RequestParam("ids")List<Integer> ids) {
        System.out.println(ids.size());
        return "user/success";
    }

    @RequestMapping(value = "/format", method = RequestMethod.GET)
    public String format(Model model) {
        FormatModel fm = new FormatModel();
        fm.setTotalCount(1234567);
        fm.setDisCount(0.51);
        fm.setSumMoney(1345.67);
        Calendar calendar = new GregorianCalendar(2015, 5, 4);
        fm.setRegisterDate(calendar.getTime());
        Calendar orderCalendar = new GregorianCalendar(2016, 5, 4, 19, 9, 30);
        fm.setOrderDate(orderCalendar.getTime());
        fm.setPhoneNum(new PhoneNum("021", "5411516"));
        model.addAttribute("fm", fm);
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println("country:" + locale.getCountry());
        System.out.println("language:" + locale.getLanguage());
        return "user/format";
    }

    @RequestMapping(value = "/format", method = RequestMethod.POST)
    public String format(@Validated FormatModel fm, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "user/format";
        }
        model.addAttribute("fm", fm);
        return "user/success";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        listModel(null, 1, model);
        return "user/list";
    }

    @RequestMapping(value = "/list/{pageNum}", method = RequestMethod.GET)
    public String list(@PathVariable int pageNum, Model model) {
        listModel(null, pageNum, model);
        return "user/list";
    }

    @RequestMapping(value = "/list/{pageNum}", method = RequestMethod.POST)
    public String list(@PathVariable int pageNum, @Validated UserFind uf, BindingResult br, Model model) {
        listModel(uf, pageNum, model);
        return "user/list";
    }

    private UserFind listModel(UserFind uf, int pageNum, Model model) {
        if (uf == null) {
            uf = new UserFind();
            uf.setRole(Role.ALL);
        }
        model.addAttribute("uf", uf);
        model.addAttribute("roles", Role.values());
        PageInfo<User> pager = userService.findByPager(uf, pageNum);
        model.addAttribute("pager", pager);
        return uf;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateRole(@RequestParam("id") int id, @RequestParam("rolevalue") String rolevalue) {
        User user = new User();
        user.setId(id);
        if (rolevalue.equals("管理员")) {
            user.setRole(Role.NORMAL);
        } else {
            user.setRole(Role.ADMIN);
        }
        User u = userService.updateRole(user);
        Gson gson = new Gson();
        String uJson = gson.toJson(u);
        return uJson;
    }

}
