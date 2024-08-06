package service;

import org.example.daos.CapabilityDao;
import org.example.daos.DatabaseConnector;
import org.example.models.Capability;
import org.example.services.CapabilityService;
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

public class CapabilityServiceTest {

    CapabilityDao mockCapabilityDao = Mockito.mock(CapabilityDao.class);
    DatabaseConnector mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);

    CapabilityService capabilityService = new CapabilityService(mockCapabilityDao, mockDatabaseConnector);

    Connection conn;

    @Test
    void getAllCapabilities_ShouldReturnCapabilities() throws SQLException {
        List<Capability> capabilityList = new ArrayList<>();

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockCapabilityDao.getAllCapabilities(conn)).thenReturn(capabilityList);

        assertEquals(capabilityList, capabilityService.getAllCapabilities());
    }

    @Test
    void getAllBands_ShouldSQLException_whenDaoReturnsSQLException() throws SQLException {

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockCapabilityDao.getAllCapabilities(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> capabilityService.getAllCapabilities());    }

}