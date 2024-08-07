package controller;

import org.example.controllers.JobRoleController;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.JobRole;
import org.example.models.JobRoleRequest;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JobRoleControllerTest {

    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController (jobRoleService);

    JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
            .roleName("Software Engineer")
            .location("Belfast")
            .capabilityID(1)
            .bandID(1)
            .closingDate(new Date(System.currentTimeMillis()))
            .description("description")
            .responsibilities( "responsibilities")
            .jobSpec("jobSpec")
            .positions(2)
            .build();

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

    JobRole jobRole = new JobRole.Builder()
            .id(3)
            .roleName("Farmer")
            .location("higgensburgh")
            .capabilityName("1")
            .bandName("2")
            .closingDate(Date.from(Instant.parse("2000-01-01T00:00:00.000Z")))
            .status("open")
            .description("farm items")
            .responsibilities("make sure corn picked")
            .jobSpec("very specific")
            .build();

    @Test
    void getJobRoleById_ShouldReturnJobRole()
            throws SQLException, DoesNotExistException,
            IllegalArgumentException {
        int id = 3;
        when(jobRoleService.getJobRoleById(id)).thenReturn(jobRole);

        Response re = jobRoleController.getJobRoleById(id);

        assertEquals(200, re.getStatus());
        assertEquals(jobRole, re.getEntity());
    }

    @Test
    void getJobRoleById_ShouldReturn404WhenServiceThrowsDoesNotExistException()
            throws SQLException, DoesNotExistException,
            IllegalArgumentException {
        int id = 2000;
        when(jobRoleService.getJobRoleById(id)).thenThrow(DoesNotExistException.class);

        Response re = jobRoleController.getJobRoleById(id);

        assertEquals(404, re.getStatus());
    }

    @Test
    void getJobRoleById_ShouldReturn500WhenServiceThrowsSQLException()
            throws SQLException, DoesNotExistException,
            IllegalArgumentException {
        int id = 1;
        when(jobRoleService.getJobRoleById(id)).thenThrow(SQLException.class);

        Response re = jobRoleController.getJobRoleById(id);

        assertEquals(500, re.getStatus());
    }

    @Test
    void createJobRole_shouldReturnId_whenInsertSuccessful()
            throws SQLException, FailedToCreateException, InvalidException {
        int expectedId = 1;

        when(jobRoleService.createJobRole(jobRoleRequest)).thenReturn(expectedId);

        Response response = jobRoleController.createJobRole(jobRoleRequest);

        assertEquals(201, response.getStatus());
        assertEquals(expectedId, (int)response.getEntity());
    }

    @Test
    void createJobRole_shouldReturn500_whenServiceThrowsSQLException()
            throws SQLException, FailedToCreateException, InvalidException {

        when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(SQLException.class);

        Response response = jobRoleController.createJobRole(jobRoleRequest);

        assertEquals(500, response.getStatus());

    }

    @Test
    void createJobRole_shouldReturn500_whenServiceThrowsFailedtoCreateException()
            throws SQLException, FailedToCreateException, InvalidException {

        when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(FailedToCreateException.class);

        Response response = jobRoleController.createJobRole(jobRoleRequest);

        assertEquals(500, response.getStatus());

    }

    @Test
    void createJobRole_shouldReturn400_whenServiceThrowsInvalidException()
            throws SQLException, FailedToCreateException, InvalidException {

        when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(InvalidException.class);

        Response response = jobRoleController.createJobRole(jobRoleRequest);

        assertEquals(400, response.getStatus());

    }

}
