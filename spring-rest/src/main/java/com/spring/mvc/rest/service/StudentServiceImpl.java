package com.spring.mvc.rest.service;

import com.spring.mvc.rest.dao.StudentDao;
import com.spring.mvc.rest.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Amysue on 2016/9/3.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void add(Student p) {

    }

    @Override
    public void update(Student p) {

    }

    @Override
    public List<Student> list() {
        return null;
    }

    @Override
    public Student get(int id) {
        return studentDao.get(id);
    }

    @Override
    public void delete(int id) {

    }
}
