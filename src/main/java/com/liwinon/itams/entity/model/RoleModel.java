package com.liwinon.itams.entity.model;

public class RoleModel {
    private int uid;
    private String uname;
    private String userid;
    private String roles;

    public RoleModel() {
    }

    public RoleModel(int uid, String uname, String userid, String roles) {
        this.uid = uid;
        this.uname = uname;
        this.userid = userid;
        this.roles = roles;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
