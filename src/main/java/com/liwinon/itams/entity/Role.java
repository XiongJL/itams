package com.liwinon.itams.entity;

import javax.persistence.*;

/**
 * 角色 , ORM 对象.
 */
@Entity
@Table(name = "ITAMS_Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private String name;
    private String workshop;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", name='" + name + '\'' +
                ", workshop='" + workshop + '\'' +
                '}';
    }
}
