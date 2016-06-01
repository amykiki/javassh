package org.mybatis.smvc.service;

import org.apache.ibatis.annotations.Param;
import org.mybatis.smvc.entity.Department;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.exception.SmvcException;
import org.mybatis.smvc.mapper.DepMapper;
import org.mybatis.smvc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/25.
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepMapper depMapper;

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
        return userMapper.findByMap(umap);
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
}
