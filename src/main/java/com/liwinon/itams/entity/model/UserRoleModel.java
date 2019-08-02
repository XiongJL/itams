package com.liwinon.itams.entity.model;


/**
 * 本类不对应数据库,他还包含角色信息.
 * 自定义实体需要包含构造方法(无参和有参数)
 */
public class UserRoleModel {
    private int uid;
    private String uname;
    private String PERSONID;
    private String pwd;
    private int rid;
    private Object name; //角色名
    private String workshop; //车间


    public UserRoleModel(){}


    public UserRoleModel(int uid, String uname, String PERSONID, String pwd, int rid, Object name, String workshop) {
        this.uid = uid;
        this.uname = uname;
        this.PERSONID = PERSONID;
        this.pwd = pwd;
        this.rid = rid;
        this.name = name;
        this.workshop = workshop;
    }

    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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

    @Override
    public String toString() {
        return "UserRoleModel{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", rid=" + rid +
                ", name=" + name +
                ", workshop='" + workshop + '\'' +
                '}';
    }
}
