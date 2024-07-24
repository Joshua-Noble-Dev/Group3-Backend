package org.example.services;

import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.models.JobRole;


import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private final JobRoleDao roleDao;
    private final DatabaseConnector databaseConnector;

    public JobRoleService(final JobRoleDao roleDao, DatabaseConnector databaseConnector) {
        this.roleDao = roleDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getAllRoles() throws SQLException {
        return roleDao.getAllJobRoles(databaseConnector.getConnection());
    }
}