package org.example.models;

public class Application {

    private int applicationId;

    private int id;

    private int userId;

    private String cvUrl;

    private String status;
    public Application(final int applicationId, final int id,
                       final int userId, final String cvUrl,
                       final String status) {
        this.applicationId = applicationId;
        this.id = id;
        this.userId = userId;
        this.cvUrl = cvUrl;
        this.status = status;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(final int applicationId) {
        this.applicationId = applicationId;
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
