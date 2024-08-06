package controller;

import org.example.controllers.BandController;
import org.example.controllers.CapabilityController;
import org.example.models.Band;
import org.example.models.Capability;
import org.example.services.BandService;
import org.example.services.CapabilityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CapabilityControllerTest {

    CapabilityService capabilityService = Mockito.mock(CapabilityService.class);
    CapabilityController capabilityController = new CapabilityController (capabilityService);

    @Test
    void GetCapabilities_ShouldReturnCapabilitiess()
            throws SQLException {
        List<Capability> capabilityList = new ArrayList<>();

        when(capabilityService.getAllCapabilities()).thenReturn(capabilityList);

        Response re = capabilityController.getCapabilities();

        assertEquals(200, re.getStatus());
        assertEquals(capabilityList, re.getEntity());
    }

    @Test
    void GetCapabilities_ShouldReturn500WhenServiceThrowsSQLException()
            throws SQLException {
        when(capabilityService.getAllCapabilities()).thenThrow(SQLException.class);

        Response re = capabilityController.getCapabilities();

        assertEquals(500, re.getStatus());
    }

}
