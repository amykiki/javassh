package org.mybatis.smvc.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.entity.UserFind;

import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/25.
 */
public interface UserMapper {
    public void add(User user);
    public void addUsers(@Param("list") List<User>list);
//    public void addUsers(@Param("userLists") List<User>users, @Param("id")List<Integer> id);

    public void addByMap(@Param("umap") Map<String, Object> umap);

    public List<User> findByMap(@Param("umap") Map<String, Object> umap);
    public List<User> findByPager(UserFind uf);
    public void update(User user);

    public List<User> listAllSendUsers(int uId);

    public List<User> list();

    public User loadByParam(int id);
    public User loadByParam(@Param("id") int id, @Param("list") List<String> list);

    public User loadLazy(int id);

    public User LoadEager(int id);

    public int countId(String username);


}
