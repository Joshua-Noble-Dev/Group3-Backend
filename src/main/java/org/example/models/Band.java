package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Band {
    @JsonProperty
    private int bandID;

    @JsonProperty
    private String bandName;

    public Band(int bandID, String bandName) {
        this.bandID = bandID;
        this.bandName = bandName;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
