package com.nf.vo;

import java.util.ArrayList;

public class AdministratorsVo {
    private Integer id ;//用户id
    private String name ;//用户名称
    private String password ;//密码
    private Boolean authority;//权限
    private String[] listing;//显示管理员可操作的数组

    @Override
    public String toString() {
        return "AdministratorsVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", authority=" + authority +
                '}';
    }

    public Boolean getAuthority() {
        return authority;
    }

    public void setAuthority(Boolean authority) {
        this.authority = authority;
        if (listing==null || listing.length==0){
            ArrayList<String> list = new ArrayList<>();
            list.add("修改账号");
            list.add("退出登入");
            if (this.authority) list.add("管理账户");
            this.listing = list.toArray(String[]::new);
        }
    }

    public String[] getListing() {
        return listing;
    }

    public void setListing(String[] listing) {
        this.listing = listing;
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
