package com.spring.mvc.rest.service;

import com.spring.mvc.rest.model.Student;

import java.util.List;

/**
 * Created by Amysue on 2016/9/3.
 */
public interface StudentService {
    public void add(Student p);
    public void update(Student p);
    public List<Student> list();
    public Student get(int id);
    public void delete(int id);
}
