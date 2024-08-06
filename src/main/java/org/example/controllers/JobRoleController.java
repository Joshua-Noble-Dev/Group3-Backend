package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.models.ApplicationRequest;
import org.example.services.JobRoleService;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
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
    @Path("/job-roles/{roleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleById(@PathParam("roleId") final int roleId) {
        try {
            return Response.ok().entity(
                    jobRoleService.getJobRoleById(roleId)).build();
        } catch (DoesNotExistException e) {
            return Response.status(
                    Response.Status.NOT_FOUND).entity(
                    e.getMessage()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/job-roles/{roleId}/apply")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response applyForJob(@PathParam("roleId") final int roleId,
                                @FormDataParam("userId")
                                    final int userId,
                                @FormDataParam("cv")
                                    final InputStream cvInputStream,
                                @FormDataParam("cv")
                                    final FormDataContentDisposition
                                            fileDetail) {
        try {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(jobRoleService.applyForJob(
                            roleId, userId, cvInputStream,
                            fileDetail.getFileName()))
                    .build();
        } catch (FailedToCreateException | SQLException e) {
            return Response.serverError().build();
        }
    }
}
