package org.example.models;

import java.util.Map;

public class UserRole {
    public static  final String ADMIN = "admin";
    public static  final String USER = "user";

    int roleId;

    private static final Map<Integer, String> rolesMap = Map.of(
            1, ADMIN,
            2, USER

    );

    public UserRole(int roleId) { setRoleId(roleId); }

    public String getRoleName() {return rolesMap.get(getRoleId()); }

    public Integer getRoleId() { return roleId; }

    private void setRoleId(int roleId) { this.roleId = roleId; }

}
