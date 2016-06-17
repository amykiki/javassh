package org.mybatis.smvc.mapper;

import org.mybatis.smvc.entity.Department;
import org.mybatis.smvc.service.CacheConstants;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by Amysue on 2016/5/31.
 */
public interface DepMapper {
    @Cacheable(value = CacheConstants.DEP, key = "#id")
    public Department load(int id);

    public List<Department> list();

    public List<Department> listInIds(List<Integer> list);

    @Cacheable(value = CacheConstants.DEP, key = "'deps'")
    public List<Integer> listAllIds();

    public int add(Department dep);

    public void delete(int id);
}
