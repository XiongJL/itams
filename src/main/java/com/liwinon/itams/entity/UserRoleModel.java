package com.liwinon.itams.entity;


/**
 * 本类不对应数据库,他还包含角色信息.
 * 自定义实体需要包含构造方法(无参和有参数)
 */
public class UserRoleModel {
    private int uid;
    private String uname;
    private String pwd;
    private Object name; //角色名
    private String workshop; //车间


    public UserRoleModel(){}

    public UserRoleModel(int uid, String uname, String pwd, Object name,String workshop) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;
        this.name = name;
        this.workshop = workshop;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }
}
