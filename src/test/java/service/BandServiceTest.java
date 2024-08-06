package service;

import org.example.daos.BandDao;
import org.example.daos.DatabaseConnector;
import org.example.models.Band;
import org.example.services.BandService;
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
public class BandServiceTest {

    BandDao mockBandDao = Mockito.mock(BandDao.class);
    DatabaseConnector mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);

    BandService bandService = new BandService(mockBandDao, mockDatabaseConnector);

    Connection conn;

    @Test
    void getAllBands_ShouldReturnBands() throws SQLException {
        List<Band> bandsList = new ArrayList<>();

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockBandDao.getAllBands(conn)).thenReturn(bandsList);

        assertEquals(bandsList, bandService.getAllBands());
    }

    @Test
    void getAllBands_ShouldSQLException_whenDaoReturnsSQLException() throws SQLException {

        Mockito.when(mockDatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(mockBandDao.getAllBands(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> bandService.getAllBands());    }

}
