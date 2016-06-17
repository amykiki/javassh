package org.mybatis.smvc.cache;

import net.sf.ehcache.CacheManager;
import org.junit.Test;
import org.mybatis.smvc.entity.User;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by Amysue on 2016/6/16.
 */
public class CacheTest {
    @Test
    public void testCache() throws IOException{
        CacheManager cacheManager = new CacheManager(new ClassPathResource("cache.xml").getInputStream());
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(cacheManager);

        User user = new User();

        int id = 1;
        user.setId(id);
        user.setUsername("amysue");
        user.setNickname("小公主");

        Cache cache = ehCacheCacheManager.getCache("user");
        cache.put(id, user);
        User u1 = (User)cache.get(id).get();
        System.out.println(u1);
        User u2 = cache.get(id, User.class);
        System.out.println(u2);
    }
}
