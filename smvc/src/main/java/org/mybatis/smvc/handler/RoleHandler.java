package org.mybatis.smvc.handler;

import org.mybatis.smvc.enums.Role;

/**
 * Created by Amysue on 2016/5/25.
 */
public class RoleHandler extends GenericEnumTypeHandler<Role> {
    public RoleHandler(Class<Role> type) {
        super(type);
    }
}
