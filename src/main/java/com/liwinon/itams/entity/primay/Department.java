package com.liwinon.itams.entity.primay;

import javax.persistence.*;

@Entity
@Table(name = "ITAMS_Department")
public class Department implements Select{
    @Id
    @Column(name = "departmentid")
    private int id;
    @Column(name = "department")
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
