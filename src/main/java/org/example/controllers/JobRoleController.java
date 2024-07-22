package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.services.JobRoleService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Job Roles API")
@Path("/api/job-roles")

public class JobRoleController {
    final JobRoleService jobRoleService;

    public JobRoleController(final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles() throws SQLException {
        return Response.ok().entity(jobRoleService.getAllRoles()).build();
    }
}