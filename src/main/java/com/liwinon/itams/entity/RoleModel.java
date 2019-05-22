package com.liwinon.itams.entity;

public class RoleModel {
    private int uid;
    private String uname;
    private String roles;

    public RoleModel() {
    }

    public RoleModel(int uid, String uname,  String roles) {
        this.uid = uid;
        this.uname = uname;
        this.roles = roles;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
