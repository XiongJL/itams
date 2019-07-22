package com.liwinon.itams.entity.primay;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ITAMS_Apply")
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;
    private int uid;
    private int rid;
    private String oldrid;  //以逗号隔开
    private Date adate;  //数据库是 datetime类型 yyyy-MM-dd HH:mm:ss.fff
    private int astate;
    private Date agrdate;
    private int agruid;
    private String reason;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getOldrid() {
        return oldrid;
    }

    public void setOldrid(String oldrid) {
        this.oldrid = oldrid;
    }

    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
    }

    public int getAstate() {
        return astate;
    }

    public void setAstate(int astate) {
        this.astate = astate;
    }

    public Date getAgrdate() {
        return agrdate;
    }

    public void setAgrdate(Date agrdate) {
        this.agrdate = agrdate;
    }

    public int getAgruid() {
        return agruid;
    }

    public void setAgruid(int agruid) {
        this.agruid = agruid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", rid=" + rid +
                ", oldrid='" + oldrid + '\'' +
                ", adate=" + adate +
                ", astate=" + astate +
                ", agrdate=" + agrdate +
                ", agruid=" + agruid +
                ", reason='" + reason + '\'' +
                '}';
    }
}
