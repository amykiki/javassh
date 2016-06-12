package org.mybatis.smvc.realm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.service.UserService;

import javax.annotation.Resource;

/**
 * Created by Amysue on 2016/6/10.
 */
public class userRealm extends AuthorizingRealm {
    private UserService userService;
    private static final Logger logger = LogManager.getLogger(userRealm.class);

    @Resource(name = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());
        User u = userService.loadByParamUsername(username, "password");
        if (u != null) {
            if (!u.getPassword().equals(password)) {
                throw new IncorrectCredentialsException(formatName(username) + " crendential not correct");
            } else {
                return new SimpleAuthenticationInfo(username, u.getPassword(), getName());
            }
        } else {
            throw new UnknownAccountException(formatName(username) + " not exists");
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    private String formatName(String username) {
        return "username [" + username + "]";
    }
}
