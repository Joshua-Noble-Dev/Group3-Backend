package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDetails {
    @JsonProperty
    private String detailName;

    @JsonProperty
    private String description;

    @JsonProperty
    private String responsibilities;

    @JsonProperty
    private String link;

    @JsonProperty
    private int roleId;

    public RoleDetails(final String detailName, final String description,
                       final String responsibilities, final String link,
                       final int roleId) {
        this.detailName = detailName;
        this.description = description;
        this.responsibilities = responsibilities;
        this.link = link;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(final int roleId) {
        this.roleId = roleId;
    }
}
