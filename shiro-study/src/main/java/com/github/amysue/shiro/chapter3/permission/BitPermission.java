package com.github.amysue.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.CollectionUtils;

import java.util.*;

/**
 * Created by Amysue on 2016/6/13.
 */
public class BitPermission implements Permission {
    private static final String WILDCARD_TOKEN = "*";
    private static final String PART_DIVIDER_TOKEN = "\\+";
    private static final String SUBPART_DIVIDER_TOKEN = ",";
    private Set<String>       resourceIdentify;
    private int               permissionBit;
    private Set<String>       instanceIds;

    public BitPermission(String wildcardString) {
        permissionBit = 0;
        setParts(wildcardString);
    }

    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof BitPermission)) {
            return false;
        }
        BitPermission bp = (BitPermission) p;
        if (this.equals(bp)) {
            return true;
        }
        if (!resourceIdentify.contains(WILDCARD_TOKEN) && !resourceIdentify.containsAll(bp.resourceIdentify)) {
            return false;
        }
        if (permissionBit != 0 && (permissionBit & bp.permissionBit) == 0) {
            return false;
        }
        if (instanceIds == null || instanceIds.contains(WILDCARD_TOKEN)) {
            return true;
        } else {
            if (bp.instanceIds == null || !instanceIds.containsAll(bp.instanceIds)) {
                return false;
            }
        }
        return true;
    }


    private void setParts(String wildcardString) {
//        System.out.println("*"+wildcardString+"*");
        if (wildcardString == null || wildcardString.trim().length() == 0 || !wildcardString.startsWith("+")) {
            throw new IllegalArgumentException("Permission String cannot be empty. Make suur permission strings are properly formatted");
        }
        wildcardString = wildcardString.trim().substring(1);
        List<String> wildStrs = CollectionUtils.asList(wildcardString.split(PART_DIVIDER_TOKEN));
        int index = 0;
        for (String wildstr : wildStrs) {
            if (index == 1) {
                if (wildstr.equals(WILDCARD_TOKEN)) {
                    permissionBit = 0;
                } else {
                    try {
                        permissionBit = Integer.parseInt(wildstr);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("BitPermission permissionBit must be Numer");
                    }
                }
            } else {
                Set<String> subpart = CollectionUtils.asSet(wildstr.split(SUBPART_DIVIDER_TOKEN));
                if (subpart.isEmpty()) {
                    throw new IllegalArgumentException("Wildcard string cannot parts with only dividers");
                }
                if (index == 0) {
                    resourceIdentify = new HashSet<>();
                    resourceIdentify = subpart;
                } else {
                    instanceIds = new HashSet<>();
                    instanceIds = subpart;
                }
            }
            index++;
        }
        if (resourceIdentify.isEmpty()) {
            throw new IllegalArgumentException("Wildcard string cannot contain only dividers. Make sure permission strings are properly formatted.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitPermission that = (BitPermission) o;

        return (Objects.equals(resourceIdentify, that.resourceIdentify)
                && (permissionBit == that.permissionBit)
                && Objects.equals(instanceIds, that.instanceIds));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resourceIdentify) + Objects.hashCode(instanceIds) + 17*permissionBit;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(resourceIdentify);
        builder.append(":");
        builder.append(permissionBit);
        if (instanceIds != null) {
            builder.append(":");
            builder.append(instanceIds);
        }
        return builder.toString();
    }
}
