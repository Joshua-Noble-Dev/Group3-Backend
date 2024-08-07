package service;

import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.JobRole;
import org.example.models.JobRoleRequest;
import org.example.services.JobRoleService;
import org.example.validators.JobRoleValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JobRoleServiceTest {

    JobRoleDao mockJobRoleDao = Mockito.mock(JobRoleDao.class);
    DatabaseConnector mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);
    JobRoleValidator mockJobRoleValidator = Mockito.mock(JobRoleValidator.class);

    JobRoleService jobRoleService = new JobRoleService(mockJobRoleDao, mockDatabaseConnector, mockJobRoleValidator);

    Connection conn;

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
    void getAllRoles_ShouldReturnRoles() throws SQLException {
        List<JobRole> jobRolesList = new ArrayList<JobRole>();

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.getAllJobRoles(conn)).thenReturn(jobRolesList);

        assertEquals(jobRolesList, jobRoleService.getAllRoles());
    }

    @Test
    void getAllRoles_ShouldThrowSQLExceptionWhenDaoThrowsSQLException()
            throws SQLException {

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.getAllJobRoles(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getAllRoles());
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
        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.getJobRoleById(id, conn)).thenReturn(jobRole);

        assertEquals(jobRole, jobRoleService.getJobRoleById(id));
    }

    @Test
    void getJobRoleById_ShouldThrowDoesNotExistExceptionWhenDaoThrowsDoesNotExistException()
            throws SQLException, DoesNotExistException,
            IllegalArgumentException {
        int id = 1000;
        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.getJobRoleById(id, conn)).thenReturn(null);

        assertThrows(DoesNotExistException.class,
                () -> jobRoleService.getJobRoleById(id));
    }

    @Test
    void getJobRoleById_ShouldThrowSQLExceptionWhenDaoThrowsSQLException()
            throws SQLException, DoesNotExistException,
            IllegalArgumentException {
        int id = 1;
        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.getJobRoleById(id, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getJobRoleById(id));
    }
    @Test
    void createJobRole_ShouldReturnId_WhenDaoReturnsId()
            throws SQLException, FailedToCreateException, InvalidException {
        int expectedResult = 1;

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.createJobRole(jobRoleRequest, conn)).thenReturn(expectedResult);

        int result = jobRoleService.createJobRole(jobRoleRequest);

        assertEquals(expectedResult,result);
    }

    @Test
    void createJobRole_ShouldThrowSQLException_WhenDaoThrowsSQLException()
            throws SQLException {

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.createJobRole(jobRoleRequest, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.createJobRole(jobRoleRequest));
    }

    @Test
    void createJobRole_ShouldThrowFailedToCreateException_whenDaoReturnsMinus1()
            throws SQLException {

        int result = -1;

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.createJobRole(jobRoleRequest, conn)).thenReturn(result);;

        assertThrows(FailedToCreateException.class,
                () -> jobRoleService.createJobRole(jobRoleRequest));
    }

    @Test
    void createJobRole_ShouldThrowInvalidException_WhenValidatorThrowsInvalidException()
            throws SQLException, InvalidException {

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleValidator.validateJobRole(jobRoleRequest)).thenThrow(InvalidException.class);

        assertThrows(InvalidException.class,
                () -> jobRoleService.createJobRole(jobRoleRequest));
    }


}
