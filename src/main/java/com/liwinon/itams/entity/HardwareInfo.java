package com.liwinon.itams.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITAMS_HardwareInfo")
public class HardwareInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String AssetsType;
    private String Brand;
    private String Model;
    private String CreateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetsType() {
        return AssetsType;
    }

    public void setAssetsType(String assetsType) {
        AssetsType = assetsType;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    @Override
    public String toString() {
        return "HardwareInfo{" +
                "id=" + id +
                ", AssetsType='" + AssetsType + '\'' +
                ", Brand='" + Brand + '\'' +
                ", Model='" + Model + '\'' +
                ", CreateDate='" + CreateDate + '\'' +
                '}';
    }
}
