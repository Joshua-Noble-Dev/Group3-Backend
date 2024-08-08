package controller;

import org.example.controllers.BandController;
import org.example.controllers.JobRoleController;
import org.example.models.Band;
import org.example.models.JobRole;
import org.example.services.BandService;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BandControllerTest {

    BandService bandService = Mockito.mock(BandService.class);
    BandController bandController = new BandController (bandService);

    @Test
    void GetBands_ShouldReturnBands()
            throws SQLException {
        List<Band> bandList = new ArrayList<>();

        when(bandService.getAllBands()).thenReturn(bandList);

        Response re = bandController.getBands();

        assertEquals(200, re.getStatus());
        assertEquals(bandList, re.getEntity());
    }

    @Test
    void GetJobRoles_ShouldReturn500WhenServiceThrowsSQLException()
            throws SQLException {
        when(bandService.getAllBands()).thenThrow(SQLException.class);

        Response re = bandController.getBands();

        assertEquals(500, re.getStatus());
    }

}
