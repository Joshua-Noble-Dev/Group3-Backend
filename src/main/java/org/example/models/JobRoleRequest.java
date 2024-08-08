package org.example.models;

import java.util.Date;

public class JobRoleRequest {

    public JobRoleRequest() {
    };

    private String roleName;
    private String location;
    private int capabilityID;
    private int bandID;
    private Date closingDate;
    private String description;
    private String responsibilities;
    private String jobSpec;
    private int positions;

    private JobRoleRequest(final Builder builder) {
        this.roleName = builder.roleName;
        this.location = builder.location;
        this.capabilityID = builder.capabilityID;
        this.bandID = builder.bandID;
        this.closingDate = builder.closingDate;
        this.description = builder.description;
        this.responsibilities = builder.responsibilities;
        this.jobSpec = builder.jobSpec;
        this.positions = builder.positions;
    }

    public static class Builder {
        private String roleName;
        private String location;
        private int capabilityID;
        private int bandID;
        private Date closingDate;
        private String description;
        private String responsibilities;
        private String jobSpec;
        private int positions;

        public JobRoleRequest.Builder roleName(final String paramRoleName) {
            this.roleName = paramRoleName;
            return this;
        }

        public JobRoleRequest.Builder location(final String paramLocation) {
            this.location = paramLocation;
            return this;
        }

        public JobRoleRequest.Builder capabilityID(
                final int paramCapabilityID) {
            this.capabilityID = paramCapabilityID;
            return this;
        }

        public JobRoleRequest.Builder bandID(final int paramBandID) {
            this.bandID = paramBandID;
            return this;
        }

        public JobRoleRequest.Builder closingDate(final Date paramClosingDate) {
            this.closingDate = paramClosingDate;
            return this;
        }

        public JobRoleRequest.Builder description(
                final String paramDescription) {
            this.description = paramDescription;
            return this;
        }

        public JobRoleRequest.Builder responsibilities(
                final String paramResponsibilities) {
            this.responsibilities = paramResponsibilities;
            return this;
        }

        public JobRoleRequest.Builder jobSpec(final String paramJobSpec) {
            this.jobSpec = paramJobSpec;
            return this;
        }

        public JobRoleRequest.Builder positions(final int paramPositions) {
            this.positions = paramPositions;
            return this;
        }

        public JobRoleRequest build() {
            return new JobRoleRequest(this);
        }
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(final int positions) {
        this.positions = positions;
    }

    public String getJobSpec() {
        return jobSpec;
    }

    public void setJobSpec(final String jobSpec) {
        this.jobSpec = jobSpec;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(final String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(final Date closingDate) {
        this.closingDate = closingDate;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(final int bandID) {
        this.bandID = bandID;
    }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabilityID(final int capabilityID) {
        this.capabilityID = capabilityID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }
}
