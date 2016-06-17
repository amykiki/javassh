package org.mybatis.smvc.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.smvc.entity.Department;
import org.mybatis.smvc.service.CacheConstants;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Amysue on 2016/6/17.
 */
@Aspect
@Order(10)
public class UpdateCacheList {
    @Resource(name = "cacheManager")
    private CacheManager cacheManager;
    private Logger logger = LogManager.getLogger(this.getClass());


    @AfterReturning(value = "execution(* org.mybatis.smvc.service.DepService.add(..))", returning = "dep")
    public void updateAdd(Department dep) {
        updateCachedList("add", dep.getId());
    }

    @AfterReturning(value = "execution(* org.mybatis.smvc.service.DepService.delete(..)) && args(deleteId)")
    public void updateDelete(int deleteId) {
        updateCachedList("delete", deleteId);
    }

    private void updateCachedList(String type, Object value) {
        Cache              cache   = cacheManager.getCache(CacheConstants.DEP);
        Cache.ValueWrapper wrapper = cache.get(CacheConstants.depsKey);
        if (wrapper != null) {
            List<Integer> list = (List<Integer>) wrapper.get();
            if (type.equals("delete")) {
                list.remove(value);
                logger.info("remove dep[" + value + "] to cached Dep list");
            } else {
                list.add((int) value);
                logger.info("add dep[" + value + "] to cached Dep list");
            }
            cache.put(CacheConstants.depsKey, list);
        }
    }
}
