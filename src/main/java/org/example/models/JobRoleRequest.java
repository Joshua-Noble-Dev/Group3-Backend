package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class JobRoleRequest {

    private String roleName;
    private String location;
    private int capabilityID;
    private int bandID;
    private Date closingDate;
    private String status;
    private String description;
    private String responsibilities;
    private String jobSpec;
    private int positions;

    @JsonCreator
    public JobRoleRequest(
            @JsonProperty("roleName") String roleName,
            @JsonProperty("location") String location,
            @JsonProperty("capabilityID") int capabilityID,
            @JsonProperty("bandID") int bandID,
            @JsonProperty("closingDate") Date closingDate,
            @JsonProperty("description") String description,
            @JsonProperty("responsibilities") String responsibilities,
            @JsonProperty("jobSpec") String jobSpec,
            @JsonProperty("positions") int positions) {
        this.roleName = roleName;
        this.location = location;
        this.capabilityID = capabilityID;
        this.bandID = bandID;
        this.closingDate = closingDate;
        this.description = description;
        this.responsibilities = responsibilities;
        this.jobSpec = jobSpec;
        this.positions = positions;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int positions) {
        this.positions = positions;
    }

    public String getJobSpec() {
        return jobSpec;
    }

    public void setJobSpec(String jobSpec) {
        this.jobSpec = jobSpec;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabilityID(int capabilityID) {
        this.capabilityID = capabilityID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
