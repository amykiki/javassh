package org.mybatis.smvc.enums;

/**
 * Created by Amysue on 2016/5/5.
 */
public enum Role implements EnumsCode{
    ALL(-1), ADMIN(0), NORMAL(1);
    private int code;

    @Override
    public int getCode() {
        return code;
    }
    Role(int code) {
        this.code = code;
    }

}
