package com.liwinon.itams.entity.primay;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ITAMS_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String uname;
    private String pwd;
    @Column(unique = true)
    private String PERSONID;
//    //mappedBy指向的是要关联的属性
//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
//    private List<Role> roles;

//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }



    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
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
}
