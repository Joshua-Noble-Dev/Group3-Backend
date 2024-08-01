package org.example.services;

import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.models.JobRole;
import org.example.models.JobRoleRequest;


import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private final JobRoleDao roleDao;
    private final DatabaseConnector databaseConnector;

    public JobRoleService(final JobRoleDao roleDao,
                          final DatabaseConnector databaseConnector) {
        this.roleDao = roleDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getAllRoles() throws SQLException {
        return roleDao.getAllJobRoles(databaseConnector.getConnection());
    }

    public int createJobRole(JobRoleRequest jobRoleRequest)
            throws SQLException {

        //jobRoleValidator.validateJobRole(jobRoleRequest);

        int id = roleDao.createJobRole(jobRoleRequest, databaseConnector.getConnection());

        if(id == -1) {
            //throw new FailedtoCreateException(Entity.JOBROLE);
        }

        return id;
    }
}
