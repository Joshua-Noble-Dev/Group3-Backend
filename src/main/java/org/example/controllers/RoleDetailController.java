package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.services.RoleDetailService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Job Roles API")
@Path("/api")
public class RoleDetailController {
    final RoleDetailService roleDetailService;

    public RoleDetailController(final RoleDetailService roleDetailService) {
        this.roleDetailService = roleDetailService;
    }
    @GET
    @Path("/job-roles/{detailId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoleInformation(final @PathParam("detailId")
                                   int detailId) throws SQLException {
        return Response.ok().entity(
                roleDetailService.getRoleInformation(detailId)).build();
    }
}
