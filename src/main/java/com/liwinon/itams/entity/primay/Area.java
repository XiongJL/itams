package com.liwinon.itams.entity.primay;

import javax.persistence.*;

@Entity
@Table(name = "ITAMS_Area")
public class Area implements Select{
    @Id
    @Column(name = "areaid")
    private int id;
    @Column(name = "area")
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

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
