package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.services.CapabilityService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Capability Api")
@Path("/api/capabilities")
public class CapabilityController {

    CapabilityService capabilityService;

    public CapabilityController(final CapabilityService capabilityService) {
        this.capabilityService = capabilityService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilities() {
        try {
            return Response.ok().entity(
                    capabilityService.getAllCapabilities())
                    .build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

}
