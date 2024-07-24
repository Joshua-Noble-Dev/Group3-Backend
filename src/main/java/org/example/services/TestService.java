package org.example.services;

import org.example.daos.RoleDetailDao;

import java.sql.SQLException;
import java.util.List;

public class TestService {
    RoleDetailDao roleDetailDao;
    public TestService(final RoleDetailDao roleDetailDao) {
        this.roleDetailDao = roleDetailDao;
    }
    public List<String> testConnection() throws SQLException {
        return roleDetailDao.testConnection();
    }
}
