package com.liwinon.itams.entity.primay;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ITAMS_Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;
    private String Event;
    private String DeviceID;
    private Date StartDate;
    private Date EndDate;
    private String State;
    private String Uname;
    private String FormID;


    public String getFormID() {
        return FormID;
    }

    public void setFormID(String formID) {
        FormID = formID;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        this.Uname = uname;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String DeviceID) {
        this.DeviceID = DeviceID;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
