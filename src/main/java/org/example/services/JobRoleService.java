package org.example.services;


import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.models.JobRole;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    private final JobRoleDao roleDao;
    private final DatabaseConnector databaseConnector;
    private final S3Uploader s3Uploader;

    public JobRoleService(final JobRoleDao roleDao,
                          final DatabaseConnector databaseConnector,
                          final S3Uploader s3Uploader) {
        this.roleDao = roleDao;
        this.databaseConnector = databaseConnector;
        this.s3Uploader = s3Uploader;
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

    public void applyForJob(final int jobId, final String applicantId,
                            final InputStream cvInputStream,
                            final String fileName) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            JobRole jobRole = roleDao.getJobRoleById(jobId, connection);

            if (jobRole.getStatus().equalsIgnoreCase(
                    "open")) {
                String cvUrl = s3Uploader.uploadCv(
                        cvInputStream, fileName, applicantId);

                roleDao.createApplication(
                        jobId, applicantId, cvUrl, "in progress", connection);
            } else {
                throw new SQLException(
                        "Job is not open or has no available positions.");
            }
        }
    }
}
