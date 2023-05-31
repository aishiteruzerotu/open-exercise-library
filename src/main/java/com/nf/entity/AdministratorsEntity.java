package com.nf.entity;

import com.nf.annotate.Auto;
import com.nf.annotate.PrimaryKey;
import com.nf.annotate.TableName;

@TableName("administrators")
public class AdministratorsEntity {
    @Auto
    @PrimaryKey
    private Integer id ;//用户id
    private String name ;//用户名称
    private String password ;//密码

    @Override
    public String toString() {
        return "AdministratorsEntity{" +
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
