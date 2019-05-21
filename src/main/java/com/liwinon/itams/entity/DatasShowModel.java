package com.liwinon.itams.entity;

/**
 * 部分数据
 */
public class DatasShowModel {
    private String AssetsType;
    private String AssetsCategory;
    private String DeviceID;
    private String AssetsID;
    private String FactoryID;
    private String AssetsName;
    private String Model;
    private String Supplier;
    private String UserName;
    private String UserID;
    private String GetTime;

    public String getAssetsType() {
        return AssetsType;
    }

    public void setAssetsType(String assetsType) {
        AssetsType = assetsType;
    }

    public String getAssetsCategory() {
        return AssetsCategory;
    }

    public void setAssetsCategory(String assetsCategory) {
        AssetsCategory = assetsCategory;
    }

    public String getAssetsName() {
        return AssetsName;
    }

    public void setAssetsName(String assetsName) {
        AssetsName = assetsName;
    }

    public String getAssetsID() {
        return AssetsID;
    }

    public void setAssetsID(String assetsID) {
        AssetsID = assetsID;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
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

    public String getGetTime() {
        return GetTime;
    }

    public void setGetTime(String getTime) {
        GetTime = getTime;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getFactoryID() {
        return FactoryID;
    }

    public void setFactoryID(String factoryID) {
        FactoryID = factoryID;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    @Override
    public String toString() {
        return "{" +
                "AssetsType='" + AssetsType + '\'' +
                ", AssetsCategory='" + AssetsCategory + '\'' +
                ", DeviceID='" + DeviceID + '\'' +
                ", AssetsID='" + AssetsID + '\'' +
                ", FactoryID='" + FactoryID + '\'' +
                ", AssetsName='" + AssetsName + '\'' +
                ", Model='" + Model + '\'' +
                ", Supplier='" + Supplier + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserID='" + UserID + '\'' +
                ", GetTime='" + GetTime + '\'' +
                '}';
    }
}
