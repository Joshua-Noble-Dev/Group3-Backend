package org.example.services;


import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.models.JobRole;
import org.example.models.JobRoleRequest;
import org.example.validators.JobRoleValidator;


import java.sql.SQLException;
import java.util.List;

public class JobRoleService {



    private final JobRoleDao roleDao;
    private final DatabaseConnector databaseConnector;
    private final JobRoleValidator jobRoleValidator;

    public JobRoleService(final JobRoleDao roleDao,
                          final DatabaseConnector databaseConnector,
                          final JobRoleValidator jobRoleValidator) {
        this.roleDao = roleDao;
        this.databaseConnector = databaseConnector;
        this.jobRoleValidator = jobRoleValidator;
    }

    public List<JobRole> getAllRoles() throws SQLException {
        return roleDao.getAllJobRoles(databaseConnector.getConnection());
    }

    public JobRole getJobRoleById(final int detailId)
            throws SQLException, DoesNotExistException {

        JobRole jobRole = roleDao.getJobRoleById(detailId,
                databaseConnector.getConnection());

        if (jobRole == null) {
            throw new DoesNotExistException(Entity.ROLE);
        } else {
            return jobRole;
        }
    }

    public int createJobRole(JobRoleRequest jobRoleRequest)
            throws SQLException, FailedToCreateException, InvalidException {

        jobRoleValidator.validateJobRole(jobRoleRequest);

        int id = roleDao.createJobRole(jobRoleRequest, databaseConnector.getConnection());

        if(id == -1) {
            throw new FailedToCreateException(Entity.JOBROLE);
        }

        return id;
    }
}
