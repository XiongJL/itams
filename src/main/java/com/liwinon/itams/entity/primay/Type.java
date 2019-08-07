package com.liwinon.itams.entity.primay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITAMS_Type")
public class Type implements Select{
    @Id
    @Column(name = "typeid")
    private int id;
    @Column(name = "type")
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
