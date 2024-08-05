package org.example.models;

import java.util.Date;

public class JobRoleResponse {

    private int id;

    private String roleName;

    private String location;

    private String capabilityName;

    private String bandName;

    private Date closingDate;

    private String status;

    public JobRoleResponse(final int id, final String roleName,
                   final String location,
                   final String capabilityName,
                   final String bandName,
                   final Date closingDate,
                   final String status) {
        this.id = id;
        this.roleName = roleName;
        this.location = location;
        this.capabilityName = capabilityName;
        this.bandName = bandName;
        this.closingDate = closingDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(final String capability) {
        this.capabilityName = capabilityName; }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(final String bandName) {
        this.bandName = bandName;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(final Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
