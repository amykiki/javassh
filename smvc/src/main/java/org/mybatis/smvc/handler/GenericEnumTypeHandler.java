package org.mybatis.smvc.handler;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.smvc.enums.EnumsCode;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/25.
 */
public class GenericEnumTypeHandler<E extends Enum<E> & EnumsCode> extends BaseTypeHandler<E>{

    private Class<E> type;
    private final Map<Integer, E> mEnums;

    public GenericEnumTypeHandler(Class<E> type) {
//        super();
        if (type == null) {
            throw new IllegalArgumentException("Type argument can't be NULL");
        }
        this.type = type;
        E[] values = type.getEnumConstants();
        if (values == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does no represent a ENUM TYPE");
        }
        mEnums = new HashedMap();
        for (E value : values) {
            int key = value.getCode();
            mEnums.put(key, value);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnum(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnum(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getEnum(i);
        }
    }

    private E getEnum(int i) {
        if (mEnums.containsKey(i)) {
            return mEnums.get(i);
        } else {
            throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by value.");
        }
    }
}
