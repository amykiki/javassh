package org.mybatis.smvc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.security.CredentialManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.Subject;
import org.mybatis.smvc.entity.Department;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.entity.UserFind;
import org.mybatis.smvc.entity.UserPsw;
import org.mybatis.smvc.exception.SmvcException;
import org.mybatis.smvc.mapper.DepMapper;
import org.mybatis.smvc.mapper.UserMapper;
import org.mybatis.smvc.realm.UserRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Amysue on 2016/5/25.
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserMapper                     userMapper;
    @Autowired
    private DepService depService;
    @Resource(name = "userRealm")
    private UserRealm userRealm;
    private @Value("${user.pageSize}") int pageSize;
    private @Value("${user.navPages}") int navPages;
    private @Value("${credential.iteration}") int credentialIteration;
    private @Value("${credential.algorithm}") String algorithm;
    private @Value("${credential.storehex}") boolean storeHex;
    private Logger logger = LogManager.getLogger(UserService.class);

    @Caching(put = {
            @CachePut(value = CacheConstants.USER, key = "#result.id"),
            @CachePut(value = CacheConstants.USER, key = "#result.username")
    })
    public User add(User user) throws SmvcException{
        Department dep = depService.load(user.getDep().getId());
        if (dep == null) {
            throw new SmvcException("dep:不存在的部门");
        }
        int count = userMapper.countId(user.getUsername());
        if (count > 0) {
            throw new SmvcException("username:用户名[" + user.getUsername() + "]已存在");
        }
        UserPsw up = getPswSalt(user.getUsername(), user.getPassword());
        user.setPassword(up.getPassword());
        user.setSalt(up.getSalt());
        int id = userMapper.add(user);
        user.setId(id);
        user.setDep(dep);
        return user;
    }

    private void loggerHit(String msg) {
        logger.info("Cache hit MISSING,[" + msg + "]");
    }

    @Cacheable(value = CacheConstants.USER, key = "#id", unless = "#result == null")
    public User load(int id) {
        loggerHit("" + id);
        User u = userMapper.LoadEager(id);
        return u;
    }

    public User loadLazy(int id) {
        return userMapper.loadLazy(id);
    }

    @Cacheable(value = CacheConstants.USER, key = "#username", unless = "#result == null")
    public User loadByUsername(String username) {
        loggerHit(username);
        User u = userMapper.loadByUsername(username);
        return u;
    }

    public void addByMap(Map<String, Object> umap) {
        userMapper.addByMap(umap);
    }

    public void addUsers(List<User> users) {
        List<Integer> ids = new ArrayList<>();
        userMapper.addUsers(users);
        if (ids != null) {
            for (int id : ids) {
                System.out.println(id);
            }
        }
    }

    public List<User> findByMap(Map<String, Object> umap) {
        PageHelper.startPage(2, 15);
        List<User> data = userMapper.findByMap(umap);
        PageInfo page = new PageInfo(data);
        return data;
    }

    public PageInfo<User> findByPager(UserFind userFind, int pageNum) {
        System.out.println("pageSize = " + pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<User> data = userMapper.findByPager(userFind);
        PageInfo pager = new PageInfo(data, navPages);
        return pager;
    }

    @Caching( put = {
            @CachePut(value = CacheConstants.USER, key = "#result.id"),
            @CachePut(value = CacheConstants.USER, key = "#result.username")
    })
    public User updateRole(User user) {
        userMapper.update(user);
        user = userMapper.LoadEager(user.getId());
        return user;
    }

    @Caching(evict = {
            @CacheEvict(value = CacheConstants.USER, key = "#id"),
            @CacheEvict(value = CacheConstants.USER, key = "#root.target.cacheUsername(#root.caches[0], #id)", condition = "#root.target.canEvict(#root.caches[0], #id)")
    })
    public void deleteById(int id) {
        userMapper.deleteById(id);
    }

    @Caching(evict = {
            @CacheEvict(value = CacheConstants.USER, key = "#username"),
            @CacheEvict(value = CacheConstants.USER, key = "#root.target.cacheUserId(#root.caches[0], #username)", condition = "#root.target.canEvict(#root.caches[0], #username)")
    })
    public void deleteByUsername(String username) {
        userMapper.deleteByUsername(username);
    }

    public void updatePassword(int id, String username, String upassword) {
        UserPsw up = getPswSalt(username, upassword);
        userMapper.setPassword(id, up.getPassword(), up.getSalt());
    }

    public List<User> listAllSendUsers(int uId) {
        return userMapper.listAllSendUsers(uId);
    }

    public List<User> list() {
        return userMapper.list();
    }

    public User loadByParam(int id) {
        return userMapper.loadByParam(id);
    }
    public User loadByParam(int id, List<String> cols) {
        return userMapper.loadByParam(id, cols);
    }

    public User loadByParamUsername(String username, String... params) {
        return userMapper.loadByParamUsername(username, Arrays.asList(params));
    }

    @Cacheable(value = CacheConstants.USER, key = "'auth' + #id")
    public UserPsw loadAuthInfo(int id) {
        return userMapper.loadAuthInfo(id);
    }

    @Cacheable(value = CacheConstants.USER, key = "'auth' + #username")
    public UserPsw loadAuthInfo(String username) {
        return userMapper.loadAuthInfoByUsername(username);
    }

    public List<String> listPermissions(String username) {
        return userMapper.listPermissions(username);
    }

    @CacheEvict(value = CacheConstants.USER, key = "'auth' + #username")
    public void updatePwd(String username, String oldPwd, String newPwd) throws SmvcException{
        /*org.apache.shiro.cache.Cache<Object, AuthenticationInfo> shiroCache = userRealm.getAuthenticationCache();
        Set<Object> keys = shiroCache.keys();
        String key1 = (String)keys.iterator().next();
        SimpleAuthenticationInfo value = (SimpleAuthenticationInfo) shiroCache.get(key1);
        Collection<AuthenticationInfo> values = shiroCache.values();*/
        UserPsw                                                  u          = loadAuthInfo(username);
        if (u == null) {
            throw new SmvcException("用户不存在");
        }
        String password = getHashPassword(u.getUsername(), oldPwd, u.getSalt());
        if (!password.equals(u.getPassword())) {
            throw new SmvcException("旧密码不正确");
        }
        updatePassword(u.getId(), u.getUsername(), newPwd);
        Subject subject = SecurityUtils.getSubject();
//        userRealm.clearCachedAuthenticationInfo(subject.getPrincipals());
        userRealm.clearCachedAuthenticationInfoByKey(u.getUsername());
    }

    private UserPsw getPswSalt(String username, String upassword) {
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        String salt = username + salt2;
        SimpleHash hash = new SimpleHash(algorithm, upassword, salt, credentialIteration);
        String password = hash.toHex();
        return new UserPsw(password, salt2);
    }

    private String getHashPassword(String username, String upassword, String salt) {
        salt = username + salt;
        SimpleHash hash = new SimpleHash(algorithm, upassword, salt, credentialIteration);
        String password = hash.toHex();
        return password;
    }
    private boolean canEvict(Cache userCache, Object key) {
        User cacheUser = getCacheUser(userCache, key);
        if (cacheUser == null) {
            return false;
        } else {
            return true;
        }
    }

    private int cacheUserId(Cache userCache, Object key) {
        User cacheUser = getCacheUser(userCache, key);
        if (cacheUser == null) {
            return -1;
        } else {
            return cacheUser.getId();
        }
    }

    private String cacheUsername(Cache userCache, Object key) {
        User cacheUser = getCacheUser(userCache, key);
        if (cacheUser == null) {
            return null;
        } else {
            return cacheUser.getUsername();
        }
    }

    private User getCacheUser(Cache userCache, Object key) {
        User cacheUser = userCache.get(key, User.class);
        return cacheUser;
    }

}
