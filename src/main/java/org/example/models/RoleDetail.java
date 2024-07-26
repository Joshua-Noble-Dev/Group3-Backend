package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class RoleDetail {
    @JsonProperty
    private int roleDetailId;

    @JsonProperty
    private String detailName;

    @JsonProperty
    private String description;

    @JsonProperty
    private String responsibilities;

    @JsonProperty
    private String link;

    @JsonProperty
    private String location;

    @JsonProperty
    private String capability;

    @JsonProperty
    private String band;

    @JsonProperty
    private Date closingDate;



    public RoleDetail(final int roleDetailId, final String detailName,
                      final String description, final String responsibilities,
                      final String link, final String location,
                      final String capability, final String band,
                      final Date closingDate) {
        this.roleDetailId = roleDetailId;
        this.detailName = detailName;
        this.description = description;
        this.responsibilities = responsibilities;
        this.link = link;
        this.location = location;
        this.capability = capability;
        this.band = band;
        this.closingDate = closingDate;
    }

    public int getRoleDetailId() {
        return roleDetailId;
    }

    public void setRoleDetailId(final int roleDetailId) {
        this.roleDetailId = roleDetailId;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(final String detailName) {
        this.detailName = detailName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(final String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(final String capability) {
        this.capability = capability;
    }

    public String getBand() {
        return band;
    }

    public void setBand(final String band) {
        this.band = band;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(final Date closingDate) {
        this.closingDate = closingDate;
    }
}
