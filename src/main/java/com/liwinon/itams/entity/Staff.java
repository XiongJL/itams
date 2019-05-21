package com.liwinon.itams.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITAMS_Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Staff;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", Staff='" + Staff + '\'' +
                '}';
    }
}
