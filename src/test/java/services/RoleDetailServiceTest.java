package services;


import org.example.daos.DatabaseConnector;
import org.example.daos.RoleDetailDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FormatException;
import org.example.exceptions.InvalidException;
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

    RoleDetailDao roleDetailDao = Mockito.mock(RoleDetailDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    RoleDetailService roleDetailService = new RoleDetailService(roleDetailDao, databaseConnector);
    Connection conn;

    @Test
    void insertEmployee_shouldReturnId_whenDaoReturnsId()
            throws DoesNotExistException, SQLException,
            FormatException, InvalidException {
        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(roleDetailDao.getRoleInformation(employeeRequest, conn)).thenReturn(expectedResult);

        int result = roleDetailService.getRoleInformation(employeeRequest);

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllRoles_ShouldThrowSQLExceptionWhenDaoThrowsSQLException() throws SQLException {

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockJobRoleDao.getAllJobRoles(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getAllRoles());
    }
}
