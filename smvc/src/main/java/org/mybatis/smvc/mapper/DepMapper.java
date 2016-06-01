package org.mybatis.smvc.mapper;

import org.mybatis.smvc.entity.Department;

import java.util.List;

/**
 * Created by Amysue on 2016/5/31.
 */
public interface DepMapper {
    public Department load(int id);

    public List<Department> list();
}
