package com.liwinon.itams.entity.primay;

import javax.persistence.*;

@Entity
@Table(name = "ITAMS_Step")
public class Step implements Select{
    @Id
    @Column(name = "stepid")
    private int id;
    @Column(name = "step")
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
