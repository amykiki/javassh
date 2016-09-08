package com.amy.spring.boot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Amysue on 2016/9/7.
 */
@Controller
@EnableAutoConfiguration
public class HelloWorldController {
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World Again!!!";
    }
}
