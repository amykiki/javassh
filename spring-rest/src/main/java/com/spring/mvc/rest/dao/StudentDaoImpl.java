package com.spring.mvc.rest.dao;

import com.spring.mvc.rest.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Amysue on 2016/9/2.
 */
@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
}
