package org.mybatis.smvc.entity;

import java.util.Objects;

/**
 * Created by Amysue on 2016/6/4.
 */
public class PhoneNum {
    private String areaCode;
    private String phoneNumber;

    public PhoneNum(String areaCode, String phoneNumber) {
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNum() {
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof PhoneNum)) return false;

        PhoneNum phoneNum = (PhoneNum) o;
        return Objects.equals(areaCode, phoneNum.areaCode) && Objects.equals(phoneNumber, phoneNum.phoneNumber);
    }

    @Override
    public int hashCode() {
//        return 7 * Objects.hashCode(areaCode) + 11 * Objects.hashCode(phoneNumber);
        return Objects.hash(areaCode, phoneNumber);
    }
}
