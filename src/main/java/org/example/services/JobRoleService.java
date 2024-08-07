package org.example.services;


import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.models.JobRole;
import org.example.utils.S3Uploader;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    public int applyForJob(final int detailId,
                           final int userId,
                           final InputStream cvInputStream,
                           final String fileName
                           )
            throws SQLException, IOException, FailedToCreateException {
        JobRole jobRole = roleDao.getJobRoleById(detailId,
                databaseConnector.getConnection());

        if (!"open".equalsIgnoreCase(jobRole.getStatus())
                || jobRole.getPositions() <= 0) {
            throw new SQLException();
        }

        String timestamp = new SimpleDateFormat(
        "yyyyMMddHHmmssSSS").format(new Date());
        String cvKey = "Group3CVs/" + fileName + "/" + timestamp;
        s3Uploader.uploadCv(cvKey, cvInputStream);

        int finalId = roleDao.createApplication(
                detailId, userId, cvKey,
                "in progress", databaseConnector.getConnection());
        if (finalId == -1) {
            throw new FailedToCreateException(Entity.APPLICATION);
        }

        return finalId;
    }
}
