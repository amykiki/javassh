package org.mybatis.smvc.service;

import net.sf.ehcache.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.smvc.entity.Department;
import org.mybatis.smvc.mapper.DepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/6/17.
 */
@Service("depService")
public class DepService {
    @Autowired
    private DepMapper depMapper;
    private Logger logger = LogManager.getLogger(DepService.class);
    @Resource(name = "cacheManager")
    private CacheManager cacheManager;


    public List<Department> cacheListDep() {
        Cache              cache      = cacheManager.getCache(CacheConstants.DEP);
        Cache.ValueWrapper o          = cache.get(CacheConstants.depsKey);
        List<Integer>      depIds;
        List<Department>   deps       = new ArrayList<>();
        List<Integer>      depIdsMiss = new ArrayList<>();
        if (o == null) {
            depIds = listAllIds();
        } else {
            depIds =  (List<Integer>) o.get();
        }

        for (int id : depIds) {
            Department dep = cache.get(id, Department.class);
            if (dep != null) {
                deps.add(dep);
            } else {
                depIdsMiss.add(id);
            }
        }
        if (depIdsMiss.size() > 0) {
            deps.addAll(listDep(depIdsMiss));
        }
        return deps;
    }

    @Cacheable(value = CacheConstants.DEP, key = "'deps'")
    public List<Integer> listAllIds() {
        List<Integer> list = depMapper.listAllIds();
        return list;
    }
    @Cacheable(value = CacheConstants.DEP, key = "#id")
    public Department load(int id) {
        return depMapper.load(id);
    }

    public List<Department> listDep(List<Integer> ids) {
        Cache cache = cacheManager.getCache(CacheConstants.DEP);
        List<Department> deps = depMapper.listInIds(ids);
        for (Department dep : deps) {
            cache.put(dep.getId(), dep);
        }
        return deps;
    }

    @CachePut(value = CacheConstants.DEP, key = "#result.id")
    public Department add(Department department) {
        int id = depMapper.add(department);
        department.setId(id);
        return department;
    }

    @CacheEvict(value = CacheConstants.DEP, key = "#id")
    public void delete(int id) {
        depMapper.delete(id);
    }


}
