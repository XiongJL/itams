package com.liwinon.itams.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "ITAMS_Assets")
public class Assets {
    @Id
    private String AssetsID;
    private String AssetsType;
    private String AssetsCategory;
    private String DeviceID;
    private String FactoryID;
    private String AssetsName;
    private String Models;
    private String Supplier;
    private String FaArea;
    private String Area;
    private String Location;
    private String Department;
    private String ProductionSetp;
    private String Personliable;
    private String PersonliableID;
    private String PState;
    private String AState;
    private String RequireID;
    private String AgreementID;
    private String ContractID;
    private Date EntryDate;
    private String ReceiptID;
    private String CheckID;
    private String OutID;
    private String Remark;

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

    public String getAssetsCategory() {
        return AssetsCategory;
    }

    public void setAssetsCategory(String assetsCategory) {
        AssetsCategory = assetsCategory;
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

    public String getAssetsName() {
        return AssetsName;
    }

    public void setAssetsName(String assetsName) {
        AssetsName = assetsName;
    }

    public String getModels() {
        return Models;
    }

    public void setModels(String model) {
        Models = model;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getFaArea() {
        return FaArea;
    }

    public void setFaArea(String faArea) {
        FaArea = faArea;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getProductionSetp() {
        return ProductionSetp;
    }

    public void setProductionSetp(String productionSetp) {
        ProductionSetp = productionSetp;
    }

    public String getPersonliable() {
        return Personliable;
    }

    public void setPersonliable(String personliable) {
        Personliable = personliable;
    }

    public String getPersonliableID() {
        return PersonliableID;
    }

    public void setPersonliableID(String personliableID) {
        PersonliableID = personliableID;
    }

    public String getPState() {
        return PState;
    }

    public void setPState(String PState) {
        this.PState = PState;
    }

    public String getAState() {
        return AState;
    }

    public void setAState(String AState) {
        this.AState = AState;
    }

    public String getRequireID() {
        return RequireID;
    }

    public void setRequireID(String requireID) {
        RequireID = requireID;
    }

    public String getAgreementID() {
        return AgreementID;
    }

    public void setAgreementID(String agreementID) {
        AgreementID = agreementID;
    }

    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String contractID) {
        ContractID = contractID;
    }

    public Date getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(Date entryDate) {
        EntryDate = entryDate;
    }

    public String getReceiptID() {
        return ReceiptID;
    }

    public void setReceiptID(String receiptID) {
        ReceiptID = receiptID;
    }

    public String getCheckID() {
        return CheckID;
    }

    public void setCheckID(String checkID) {
        CheckID = checkID;
    }

    public String getOutID() {
        return OutID;
    }

    public void setOutID(String outID) {
        OutID = outID;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public String toString() {
        return "{" +
                "资产号='" + AssetsID + '\'' +
                ", 资产类型='" + AssetsType + '\'' +
                ", 资产类别='" + AssetsCategory + '\'' +
                ", 设备编号='" + DeviceID + '\'' +
                ", FactoryID='" + FactoryID + '\'' +
                ", AssetsName='" + AssetsName + '\'' +
                ", Models='" + Models + '\'' +
                ", Supplier='" + Supplier + '\'' +
                ", FaArea='" + FaArea + '\'' +
                ", Area='" + Area + '\'' +
                ", Location='" + Location + '\'' +
                ", Department='" + Department + '\'' +
                ", ProductionSetp='" + ProductionSetp + '\'' +
                ", Personliable='" + Personliable + '\'' +
                ", PersonliableID='" + PersonliableID + '\'' +
                ", PState='" + PState + '\'' +
                ", AState='" + AState + '\'' +
                ", RequireID='" + RequireID + '\'' +
                ", AgreementID='" + AgreementID + '\'' +
                ", ContractID='" + ContractID + '\'' +
                ", EntryDate='" + EntryDate + '\'' +
                ", ReceiptID='" + ReceiptID + '\'' +
                ", CheckID='" + CheckID + '\'' +
                ", OutID='" + OutID + '\'' +
                ", Remark='" + Remark + '\'' +
                '}';
    }
}
