package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class JobRoles {

    @JsonProperty
    private String roleName;

    @JsonProperty
    private String location;

    @JsonProperty
    private String capability;

    @JsonProperty
    private String band;

    @JsonProperty
    private Date closingDate;

    @JsonProperty
    private String status;

    public JobRoles(String roleName, String location, String capability,
                   String band, Date closingDate, String status) {
        this.roleName = roleName;
        this.location = location;
        this.capability = capability;
        this.band = band;
        this.closingDate = closingDate;
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}