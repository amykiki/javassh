package org.mybatis.smvc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.smvc.entity.Department;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.entity.UserFind;
import org.mybatis.smvc.enums.Role;
import org.mybatis.smvc.exception.SmvcException;
import org.mybatis.smvc.mapper.DepMapper;
import org.mybatis.smvc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/25.
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserMapper                     userMapper;
    @Autowired
    private DepMapper                      depMapper;
    private @Value("${user.pageSize}") int pageSize;
    private @Value("${user.navPages}") int navPages;

    public void add(User user) throws SmvcException{
        Department dep = depMapper.load(user.getDep().getId());
        if (dep == null) {
            throw new SmvcException("dep:不存在的部门");
        }
        int count = userMapper.countId(user.getUsername());
        if (count > 0) {
            throw new SmvcException("username:用户名[" + user.getUsername() + "]已存在");
        }
        userMapper.add(user);
    }

    public User load(int id) {
        User u = userMapper.LoadEager(id);
        return u;
    }

    public List<Department> listDep() {
        return depMapper.list();
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

    public PageInfo<User> findByPager(UserFind userFind) {
        System.out.println("pageSize = " + pageSize);
        PageHelper.startPage(userFind.getPageNum(), pageSize);
        List<User> data = userMapper.findByPager(userFind);
        PageInfo pager = new PageInfo(data, navPages);
        return pager;
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

    public void updatePwd(int id, String oldPwd, String newPwd) throws SmvcException{
        User u = userMapper.loadLazy(id);
        if (u == null) {
            throw new SmvcException("用户不存在");
        }
        if (!u.getPassword().equals(oldPwd)) {
            throw new SmvcException("旧密码不正确");
        }

        User nu = new User();
        nu.setId(u.getId());
        nu.setPassword(newPwd);
        userMapper.update(nu);
    }

    public User updateRole(User user) {
        userMapper.update(user);
        user = userMapper.LoadEager(user.getId());
        return user;
    }
}
