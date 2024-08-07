package org.example.models;

import java.util.Map;

public class UserRole {
    public static  final String ADMIN = "admin";
    public static  final String USER = "user";

    int roleId;

    private static final Map<Integer, String> RolesMap = Map.of(
            1, ADMIN,
            2, USER

    );

    public UserRole(final int roleId) {
        setRoleId(roleId); }

    private void setRoleId(final int roleId) {
        this.roleId = roleId; }

    public String getRoleName() {
        return RolesMap.get(getRoleId()); }

    public Integer getRoleId() {
        return roleId; }



}
