package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Capability {

    @JsonProperty
    private int capabilityID;

    @JsonProperty
    private String capabilityName;

    public Capability(final int capabilityID,
                      final String capabilityName) {
        this.capabilityID = capabilityID;
        this.capabilityName = capabilityName;
    }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabilityID(final int capabilityID) {
        this.capabilityID = capabilityID;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(final String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
