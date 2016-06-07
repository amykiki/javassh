package org.mybatis.smvc.entity;

import org.mybatis.smvc.formatter.PhoneNumFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

/**
 * Created by Amysue on 2016/6/4.
 */
public class FormatModel {
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,###")
    private int    totalCount;
    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private double disCount;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double sumMoney;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date   registerDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date   orderDate;

    @PhoneNumFormat
    private PhoneNum phoneNum;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getDisCount() {
        return disCount;
    }

    public void setDisCount(double disCount) {
        this.disCount = disCount;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public PhoneNum getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(PhoneNum phoneNum) {
        this.phoneNum = phoneNum;
    }
}
