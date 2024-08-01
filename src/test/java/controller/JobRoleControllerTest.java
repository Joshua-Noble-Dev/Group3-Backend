package controller;

import org.example.controllers.JobRoleController;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.example.models.JobRoleRequest;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JobRoleControllerTest {

    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController (jobRoleService);

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Software Engineer",
            "Belfast",
            1,
            1,
            new Date(System.currentTimeMillis()),
            "open",
            "description",
            "responsibilities",
            "jobSpec",
            2

    );

    @Test
    void GetJobRolesShouldReturnJobRoles()
            throws SQLException {
        List<JobRole> jobRolesList = new ArrayList<>();

        when(jobRoleService.getAllRoles()).thenReturn(jobRolesList);

        Response re = jobRoleController.getAllJobRoles();

        assertEquals(200, re.getStatus());
        assertEquals(jobRolesList, re.getEntity());
    }

    @Test
    void GetJobRoles_ShouldReturn500WhenServiceThrowsSQLException()
            throws SQLException {
        when(jobRoleService.getAllRoles()).thenThrow(SQLException.class);

        Response re = jobRoleController.getAllJobRoles();

        assertEquals(500, re.getStatus());
    }

    @Test
    void createJobRole_ShouldReturnId_WhenInsertSuccessful()
            throws SQLException, FailedToCreateException, InvalidException {

        int expectedId = 1;

        when(jobRoleService.createJobRole(jobRoleRequest)).thenReturn(expectedId);

        Response response = jobRoleController.createJobRole(jobRoleRequest);

        assertEquals(201,response.getStatus());

        assertEquals(expectedId, (int)response.getEntity());
    }

    @Test
    void createJobRole_ShouldReturn500_WhenServiceThrowsSQLException()
            throws SQLException, FailedToCreateException, InvalidException {

        when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(SQLException.class);

        Response response = jobRoleController.createJobRole(jobRoleRequest);

        assertEquals(500,response.getStatus());

    }

    @Test
    void createJobRole_ShouldReturn500_WhenServiceThrowsFailedToCreateException()
            throws SQLException, FailedToCreateException, InvalidException {

        when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(FailedToCreateException.class);

        Response response = jobRoleController.createJobRole(jobRoleRequest);

        assertEquals(500,response.getStatus());

    }

    @Test
    void createJobRole_ShouldReturn400_WhenServiceThrowsInvalidException()
            throws SQLException, FailedToCreateException, InvalidException {

        when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(InvalidException.class);

        Response response = jobRoleController.createJobRole(jobRoleRequest);

        assertEquals(400,response.getStatus());

    }



}
