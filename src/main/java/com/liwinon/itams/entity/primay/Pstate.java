package com.liwinon.itams.entity.primay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITAMS_Pstate")
public class Pstate implements Select{
    @Id
    @Column(name = "pstateid")
    private int id;
    @Column(name = "state")
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
