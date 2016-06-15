package com.github.amysue.shiro.chapter5.credentials;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Amysue on 2016/6/15.
 */
public class RetyrLimitsHashedCredentialMatcher extends HashedCredentialsMatcher {
    private Ehcache passwordRetryCache;

    public RetyrLimitsHashedCredentialMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("chapter5/ehcache.xml"));
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");

    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        Element element = passwordRetryCache.get(username);
        if (element == null) {
            element = new Element(username, new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retyrCount = (AtomicInteger) element.getObjectValue();
        if (retyrCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException("密码输错次数超过5次，一小时内不能再登陆");
        }
        boolean match = super.doCredentialsMatch(token, info);
        if (match) {
            passwordRetryCache.remove(username);
        }
        return match;
    }
}
