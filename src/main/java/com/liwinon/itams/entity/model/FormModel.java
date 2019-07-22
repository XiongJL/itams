package com.liwinon.itams.entity.model;

/**
 * 用于传输 报废,转售的信息Model
 */
public class FormModel {
    private String AssetsType;
    private String AssetsCategory;
    private String AssetsID;
    private String DeviceID;
    private String AssetsName;
    private String Model;
    private String Event;   //处置类型
    private String Location;  //设备位置
    private String PState;
    private String UserID;

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

    public String getAssetsID() {
        return AssetsID;
    }

    public void setAssetsID(String assetsID) {
        AssetsID = assetsID;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getAssetsName() {
        return AssetsName;
    }

    public void setAssetsName(String assetsName) {
        AssetsName = assetsName;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPState() {
        return PState;
    }

    public void setPState(String PState) {
        this.PState = PState;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
