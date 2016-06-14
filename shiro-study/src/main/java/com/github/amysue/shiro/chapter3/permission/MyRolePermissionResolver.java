package com.github.amysue.shiro.chapter3.permission;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.util.CollectionUtils;

import java.util.Collection;

/**
 * Created by Amysue on 2016/6/13.
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if ("role1".equals(roleString)) {
            return CollectionUtils.asList((Permission)new WildcardPermission("menu:*"));
        }
        return null;
    }
}
