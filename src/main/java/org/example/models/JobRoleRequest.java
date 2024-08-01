package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class JobRoleRequest {

    private String roleName;
    private String location;
    private int capability;
    private int band;
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
            @JsonProperty("capability") int capability,
            @JsonProperty("band") int band,
            @JsonProperty("closingDate") Date closingDate,
            @JsonProperty("status") String status,
            @JsonProperty("description") String description,
            @JsonProperty("responsibilities") String responsibilities,
            @JsonProperty("jobSpec") String jobSpec,
            @JsonProperty("positions") int positions) {
        this.roleName = roleName;
        this.location = location;
        this.capability = capability;
        this.band = band;
        this.closingDate = closingDate;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public int getBand() {
        return band;
    }

    public void setBand(int band) {
        this.band = band;
    }

    public int getCapability() {
        return capability;
    }

    public void setCapability(int capability) {
        this.capability = capability;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
