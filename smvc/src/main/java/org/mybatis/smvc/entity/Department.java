package org.mybatis.smvc.entity;

import java.io.Serializable;

/**
 * Created by Amysue on 2016/5/25.
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 1954118678255560438L;
    private int id;
    private String name;

    public Department() {
    }

    public Department(int id) {
        this.id = id;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
