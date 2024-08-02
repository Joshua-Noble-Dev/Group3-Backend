package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.services.BandService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Band Api")
@Path("/api/bands")
public class BandController {

    BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBands() {
        try {
            return Response.ok().entity(bandService.getAllBands()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }
}
