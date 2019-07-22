package com.liwinon.itams.entity.model;

public class DatasModel {
    private String AssetsID;
    private String AssetsType;
    private String Brand;
    private String Model;
    private String UserName;
    private String UserID;
    private String telephone;
    private String Lphone;
    private String GetTime;
    private String UserDepartment;
    private String CPU;
    private String RAM;
    private String HDD1;
    private String HDD2;
    private String WiredMAC1;
    private String WiredMAC2;
    private String WirelessMAC;
    private String Location;
    private int State;
    private String PurchaseDate;
    private String Staff;
    private String EntryDate;
    private String Department;
    private String Remark;

    @Override
    public String toString() {
        String state;
        if(State==0){
            state = "未使用";
        }else{
            state = "使用中";
        }
        return "{" +
                "资产号='" + AssetsID + '\'' +
                ", 类型='" + AssetsType + '\'' +
                ", 品牌='" + Brand + '\'' +
                ", 型号='" + Model + '\'' +
                ", 使用人='" + UserName + '\'' +
                ", 工号='" + UserID + '\'' +
                ", 电话='" + telephone + '\'' +
                ", 座机='" + Lphone + '\'' +
                ", 获取时间='" + GetTime + '\'' +
                ", 使用部门='" + UserDepartment + '\'' +
                ", CPU='" + CPU + '\'' +
                ", RAM='" + RAM + '\'' +
                ", HDD1='" + HDD1 + '\'' +
                ", HDD2='" + HDD2 + '\'' +
                ", 有线MAC='" + WiredMAC1 + '\'' +
                ", 有线MAC2='" + WiredMAC2 + '\'' +
                ", 无线MAC='" + WirelessMAC + '\'' +
                ", 厂别='" + Location + '\'' +
                ", 状态=" + " ' "+state + "'"+
                ", PurchaseDate='" + PurchaseDate + '\'' +
                ", Staff='" + Staff + '\'' +
                ", EntryDate='" + EntryDate + '\'' +
                ", Department='" + Department + '\'' +
                ", Remark='" + Remark + '\'' +
                '}';
    }

    public String getUserDepartment() {
        return UserDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        UserDepartment = userDepartment;
    }

    public String getAssetsID() {
        return AssetsID;
    }

    public void setAssetsID(String assetsID) {
        AssetsID = assetsID;
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

    public String getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String staff) {
        Staff = staff;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
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

    public String getGetTime() {
        return GetTime;
    }

    public void setGetTime(String getTime) {
        GetTime = getTime;
    }
}
