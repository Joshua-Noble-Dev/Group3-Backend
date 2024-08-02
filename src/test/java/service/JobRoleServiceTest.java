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

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Software Engineer",
            "Belfast",
            1,
            1,
            new Date(System.currentTimeMillis()),

            "description",
            "responsibilities",
            "jobSpec",
            2

    );

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
    void createJobRole_ShouldThrowFailedtoCreateException_whenValidatorThrowsSalaryTooLowException()
            throws SQLException {

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.createJobRole(jobRoleRequest, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.createJobRole(jobRoleRequest));
        assertThrows(FailedToCreateException.class, () -> jobRoleService.createJobRole(jobRoleRequest), "Role Name greater than 100 characters");
    }


}
