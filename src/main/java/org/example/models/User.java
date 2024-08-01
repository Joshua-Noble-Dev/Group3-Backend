package org.example.models;

public class User {

    private String username;

    private String password;

    private int userRoleID;

    public User(final String username, final String password,
                final int userRoleID) {
        this.username = username;
        this.password = password;
        this.userRoleID = userRoleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(final int userRoleID) {
        this.userRoleID = userRoleID;
    }
}
