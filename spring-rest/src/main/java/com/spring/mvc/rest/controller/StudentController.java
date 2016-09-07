package com.spring.mvc.rest.controller;

import com.spring.mvc.rest.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Amysue on 2016/9/5.
 */
@Controller
@RequestMapping("/stu")
public class StudentController {
    private StudentService studentService;

    @Resource(name = "studentService")
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
