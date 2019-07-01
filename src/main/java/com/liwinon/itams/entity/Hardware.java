package com.liwinon.itams.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ITAMS_Hardware")
public class Hardware implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String AssetsID;
    private String ITtype;
    private String Brand;
    private String Model;
    private String CPU;
    private String RAM;
    private String HDD1;
    private String HDD2;
    private String WiredMAC1;
    private String WiredMAC2;
    private String WirelessMAC;
    private String Location;
    private int State;
    private Date PurchaseDate;
    private String Staff;
    private Date EntryDate;
    private String Department;
    private String Remark;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetsID() {
        return AssetsID;
    }

    public void setAssetsID(String assetsID) {
        AssetsID = assetsID;
    }

    public String getITtype() {
        return ITtype;
    }

    public void setITtype(String ITtype) {
        this.ITtype = ITtype;
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

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getHDD1() {
        return HDD1;
    }

    public void setHDD1(String HDD1) {
        this.HDD1 = HDD1;
    }

    public String getHDD2() {
        return HDD2;
    }

    public void setHDD2(String HDD2) {
        this.HDD2 = HDD2;
    }

    public String getWiredMAC1() {
        return WiredMAC1;
    }

    public void setWiredMAC1(String wiredMAC1) {
        WiredMAC1 = wiredMAC1;
    }

    public String getWiredMAC2() {
        return WiredMAC2;
    }

    public void setWiredMAC2(String wiredMAC2) {
        WiredMAC2 = wiredMAC2;
    }

    public String getWirelessMAC() {
        return WirelessMAC;
    }

    public void setWirelessMAC(String wirelessMAC) {
        WirelessMAC = wirelessMAC;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String staff) {
        Staff = staff;
    }

    public Date getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(Date entryDate) {
        EntryDate = entryDate;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "id=" + id +
                ", AssetsID='" + AssetsID + '\'' +
                ", AssetsType='" + ITtype + '\'' +
                ", Brand='" + Brand + '\'' +
                ", Model='" + Model + '\'' +
                ", CPU='" + CPU + '\'' +
                ", RAM='" + RAM + '\'' +
                ", HDD1='" + HDD1 + '\'' +
                ", HDD2='" + HDD2 + '\'' +
                ", WiredMAC1='" + WiredMAC1 + '\'' +
                ", WiredMAC2='" + WiredMAC2 + '\'' +
                ", WirelessMAC='" + WirelessMAC + '\'' +
                ", Location='" + Location + '\'' +
                ", State='" + State + '\'' +
                ", PurchaseDate='" + PurchaseDate + '\'' +
                ", Staff='" + Staff + '\'' +
                ", EntryDate='" + EntryDate + '\'' +
                ", Department='" + Department + '\'' +
                ", Remark='" + Remark + '\'' +
                '}';
    }
}
