package org.mybatis.smvc.realm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.entity.UserPsw;
import org.mybatis.smvc.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Amysue on 2016/6/10.
 */
public class UserRealm extends AuthorizingRealm {
    private UserService userService;
    private static final Logger logger = LogManager.getLogger(UserRealm.class);

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
        String  username = (String) authenticationToken.getPrincipal();
        UserPsw u        = userService.loadAuthInfo(username);
        if (u != null) {
            String                   password = u.getPassword();
            String                   salt     = u.getSalt();
            SimpleAuthenticationInfo ai       = new SimpleAuthenticationInfo(u, password, getName());
            ai.setCredentialsSalt(ByteSource.Util.bytes(username + salt));
            return ai;
        } else {
            throw new UnknownAccountException(formatName(username) + " not exists");
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserPsw u = (UserPsw) principalCollection.fromRealm(getName()).iterator().next();
        if (u != null) {
            String username = u.getUsername();
            User user = userService.load(u.getId());
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole(user.getRole().toString());
            List<String> perms = userService.listPermissions(username);
            for (String perm : perms) {
                info.addStringPermission(perm);
            }
            return info;
        }
        return null;
    }

    private String formatName(String username) {
        return "username [" + username + "]";
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    public void clearCachedAuthenticationInfoByKey(Object key) {
        getAuthenticationCache().remove(key);
    }


}
