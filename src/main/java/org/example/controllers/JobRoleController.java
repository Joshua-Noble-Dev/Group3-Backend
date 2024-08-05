package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
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
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleById(@PathParam("id") final int id) {
        try {
            return Response.ok().entity(
                    jobRoleService.getJobRoleById(id)).build();
        } catch (DoesNotExistException e) {
            return Response.status(
                    Response.Status.NOT_FOUND).entity(
                    e.getMessage()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/job-roles/{jobId}/apply")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response applyForJob(
            final @PathParam("jobId") int jobId,
            final @FormDataParam("applicantId") String applicantId,
            final @FormDataParam("cvFile") InputStream cvInputStream,
            final @FormDataParam("cvFile")
            FormDataContentDisposition fileDetail) {
        try {
            jobRoleService.applyForJob(jobId,
                    applicantId, cvInputStream, fileDetail.getFileName());
            return Response.ok().entity(
                    "Application submitted successfully").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to apply for the job").build();
        }
    }
}
