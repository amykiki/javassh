package org.mybatis.smvc.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.smvc.entity.User;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Amysue on 2016/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:beans.xml"})
@ContextConfiguration(locations = {"classpath*:spring-cache.xml"})
public class CacheXMLTest {
    private EhCacheCacheManager cacheCacheManager;

    @Resource(name = "cacheManager")
    public void setCacheCacheManager(EhCacheCacheManager cacheCacheManager) {
        this.cacheCacheManager = cacheCacheManager;
    }

    @Test
    public void testCache() {
        User user = new User();
        int id = 1;
        user.setId(id);
        user.setUsername("amysue");
        user.setNickname("小公主");

        Cache cache = cacheCacheManager.getCache("user");
        cache.put(id, user);
        Assert.assertNotNull(cache.get(id));
        User u1 = (User)cache.get(id).get();
        System.out.println(u1);
        User u2 = cache.get(id, User.class);
        System.out.println(u2);
    }
}
