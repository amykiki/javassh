package org.mybatis.smvc.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.entity.UserFind;
import org.mybatis.smvc.entity.UserPsw;

import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/25.
 */
public interface UserMapper {
    public int add(User user);
    public void addUsers(@Param("list") List<User>list);
//    public void addUsers(@Param("userLists") List<User>users, @Param("id")List<Integer> id);

    public void addByMap(@Param("umap") Map<String, Object> umap);

    public List<User> findByMap(@Param("umap") Map<String, Object> umap);
    public List<User> findByPager(UserFind uf);
    public void update(User user);

    public void setPassword(@Param("id") int id, @Param("password") String password, @Param("salt") String salt);

    public List<User> listAllSendUsers(int uId);

    public List<User> list();

    public UserPsw loadAuthInfo(int id);
    public UserPsw loadAuthInfoByUsername(String username);

    public User loadByParam(int id);
    public User loadByParam(@Param("id") int id, @Param("list") List<String> list);

    public User loadLazy(int id);

    public User LoadEager(int id);

    public User loadByUsername(String username);
    public User loadByParamUsername(@Param("username") String username, @Param("list") List<String> list);

    public int countId(String username);

    public void deleteById(int id);

    public void deleteByUsername(String username);

    public List<String> listPermissions(String username);

}
