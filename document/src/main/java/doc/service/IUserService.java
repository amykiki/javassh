package doc.service;

import doc.dto.Pager;
import doc.entity.User;
import doc.exception.DocException;

import java.util.Map;

/**
 * Created by Amysue on 2016/5/7.
 */
public interface IUserService {
    public void add(User user, int depId) throws DocException;

    public void delete(int id);

    public User load(int id);

    public User loadLazyByUsername(String username);
    public User loadEagerByUsername(String username);
    public User loadLazyById(int id);
    public User loadEagerById(int id);
    public void update(User user, int depId) throws DocException;

//    public void update(User user) throws DocException;

    public Pager<User> findUser(Map<String, Object> params);


}
