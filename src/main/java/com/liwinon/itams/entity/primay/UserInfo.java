package com.liwinon.itams.entity.primay;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ITAMS_UserInfo")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String DeviceID;
    private String UserName;
    private String UserID;
    private String UserDepartment;
    private String telephone;
    private String Lphone;
    private Date GetTime;

    public Date getGetTime() {
        return GetTime;
    }

    public void setGetTime(Date getTime) {
        GetTime = getTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserDepartment() {
        return UserDepartment;
    }

    public void setUserDepartment(String department) {
        UserDepartment = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLphone() {
        return Lphone;
    }

    public void setLphone(String lphone) {
        Lphone = lphone;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", DeviceID='" + DeviceID + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserID='" + UserID + '\'' +
                ", UserDepartment='" + UserDepartment + '\'' +
                ", telephone='" + telephone + '\'' +
                ", Lphone='" + Lphone + '\'' +
                ", GetTime=" + GetTime +
                '}';
    }
}
