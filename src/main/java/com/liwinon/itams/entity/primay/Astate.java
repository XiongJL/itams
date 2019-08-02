package com.liwinon.itams.entity.primay;

import javax.persistence.*;

@Entity
@Table(name = "ITAMS_Astate")
public class Astate implements Select{
    @Id
    @Column(name = "astateid")
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
