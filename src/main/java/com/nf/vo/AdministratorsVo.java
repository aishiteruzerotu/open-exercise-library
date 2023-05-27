package com.nf.vo;

import com.nf.annotate.Auto;
import com.nf.annotate.PrimaryKey;

public class AdministratorsVo {
    @Auto
    @PrimaryKey
    private Integer id ;//用户id
    private String name ;//用户名称
    private String password ;//密码

    @Override
    public String toString() {
        return "AdministratorsVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
