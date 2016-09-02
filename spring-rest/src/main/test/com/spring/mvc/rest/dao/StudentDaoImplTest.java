package com.spring.mvc.rest.dao;

import com.spring.mvc.rest.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jdbcMysql.xml"})
public class StudentDaoImplTest {
    @Resource(name = "studentDao")
    private StudentDao studentDao;
    @Test
    public void add() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void delete1() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void get() throws Exception {
        Student stu = studentDao.get(1);
        System.out.println(stu);
    }

    @Test
    public void load() throws Exception {

    }

    @Test
    public void list() throws Exception {

    }

}