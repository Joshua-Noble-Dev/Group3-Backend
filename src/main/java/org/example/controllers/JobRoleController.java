package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.models.ApplicationRequest;
import org.example.services.JobRoleService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.sql.SQLException;

@Api("Job Roles API")
@Path("/api")
public class JobRoleController {
    private final JobRoleService jobRoleService;

    public JobRoleService getJobRoleService() {
        return jobRoleService;
    }

    public JobRoleController(final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }


    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles() {
        try {
            return Response.ok().entity(jobRoleService.getAllRoles()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GET
    @Path("/job-roles/{pathId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleById(@PathParam("pathId") final int pathId) {
        try {
            return Response.ok().entity(
                    jobRoleService.getJobRoleById(pathId)).build();
        } catch (DoesNotExistException e) {
            return Response.status(
                    Response.Status.NOT_FOUND).entity(
                    e.getMessage()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/job-roles/{pathId}/apply")
    @Produces(MediaType.APPLICATION_JSON)
    public Response applyForJob(@PathParam("pathId") final int pathId,
                                final ApplicationRequest applicationRequest,
                                final InputStream cvInputStream) {
        try {
            return Response.ok().entity(
                    jobRoleService.applyForJob(
                            pathId, applicationRequest, cvInputStream)).build();
        } catch (FailedToCreateException | SQLException e) {
            return Response.serverError().build();
        }
    }
}
