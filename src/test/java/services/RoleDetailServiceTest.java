package services;


import org.example.daos.DatabaseConnector;
import org.example.daos.RoleDetailDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FormatException;
import org.example.exceptions.InvalidException;
import org.example.models.RoleDetail;
import org.example.services.RoleDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RoleDetailServiceTest {

    RoleDetailDao mockRoleDetailDao = Mockito.mock(RoleDetailDao.class);
    DatabaseConnector mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);
    RoleDetailService roleDetailService = new RoleDetailService(mockRoleDetailDao, mockDatabaseConnector);
    Connection conn;

//    @Test
//    void getRoleDetail_ShouldReturnRoleDetail()
//            throws SQLException, DoesNotExistException {
//        String detailId = "1";
//        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(mockRoleDetailDao.getRoleInformation(
//                Integer.parseInt(detailId), conn)).thenReturn(jobRolesList);
//
//        assertEquals(jobRolesList, jobRoleService.getAllRoles());
//    }

//    @Test
//    void getAllRoles_ShouldReturnRoles() throws SQLException {
//        List<JobRole> jobRolesList = new ArrayList<JobRole>();
//
//        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(mockJobRoleDao.getAllJobRoles(conn)).thenReturn(jobRolesList);
//
//        assertEquals(jobRolesList, jobRoleService.getAllRoles());
//    }

//    @Test
//    void getRoleDetail_ShouldThrowSQLExceptionWhenDaoThrowsSQLException() throws SQLException {
//        int detailId = 800;
//        Mockito.when(DatabaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(mockRoleDetailDao.getRoleInformation(detailId, conn)).thenThrow(SQLException.class);
//
//        assertThrows(SQLException.class,
//                () -> roleDetailService.getRoleInformation(
//                        String.valueOf(detailId)));
//    }

    @Test
    void getRoleDetail_ShouldThrowDoesNotExistExceptionWhenDaoThrowsDoesNotExistException()
            throws DoesNotExistException, SQLException {
        String detailId = "200";
        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockRoleDetailDao.getRoleInformation(
                Integer.parseInt(detailId), conn)).thenReturn(null);

        assertThrows(DoesNotExistException.class,
                () -> roleDetailService.getRoleInformation(detailId));
    }

//    @Test
//    void getEmployee_shouldThrowDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException {
//        int employeeId = 256;
//        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(employeeDao.getEmployee(employeeId, conn)).thenReturn(null);
//
//        assertThrows(DoesNotExistException.class,
//                () -> employeeService.getEmployee(employeeId));
//    }
}
