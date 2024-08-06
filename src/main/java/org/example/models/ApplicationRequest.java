package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationRequest {

    private int id;

    private int userId;

    private String cvUrl;

    private String status;

    @JsonCreator
    public ApplicationRequest(@JsonProperty("id") final int id,
                              @JsonProperty("userId") final int userId,
                              @JsonProperty("cvUrl") final String cvUrl,
                              @JsonProperty("status") final String status) {
        this.id = id;
        this.userId = userId;
        this.cvUrl = cvUrl;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(final String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
