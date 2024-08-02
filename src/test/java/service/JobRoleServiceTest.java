package service;

import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.exceptions.DoesNotExistException;
import org.example.models.JobRole;
import org.example.services.JobRoleService;
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

    JobRoleService jobRoleService = new JobRoleService(mockJobRoleDao, mockDatabaseConnector);

    Connection conn;

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
}
