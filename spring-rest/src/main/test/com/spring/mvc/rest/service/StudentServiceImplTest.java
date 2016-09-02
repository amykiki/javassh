package com.spring.mvc.rest.service;

import com.spring.mvc.rest.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/9/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jdbcMysql.xml"})
public class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;
    @Test
    public void get() throws Exception {
        int id = 1;
        Student stu = studentService.get(id);
        System.out.println(stu);
    }

}