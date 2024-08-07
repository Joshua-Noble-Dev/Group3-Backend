package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.JobRoleRequest;
import org.example.exceptions.DoesNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.example.models.JobRole;
import org.example.models.UserRole;
import org.example.services.JobRoleService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Job Roles API")
@Path("/api/job-roles")

public class JobRoleController {
    private final JobRoleService jobRoleService;

    public JobRoleService getJobRoleService() {
        return jobRoleService;
    }

    public JobRoleController(final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN, UserRole.USER})
    @ApiOperation(
            value = "Returns all job roles",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = JobRole.class)
    public Response getAllJobRoles() {
        try {
            return Response.ok().entity(jobRoleService.getAllRoles()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN, UserRole.USER})
    @ApiOperation(
            value = "Returns job roles by ID",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = JobRole.class)
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
    @Produces (MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN})
    @ApiOperation(
            value = "Creates a job roles",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = JobRole.class)
    public Response createJobRole(final JobRoleRequest jobRoleRequest) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(jobRoleService.createJobRole(jobRoleRequest))
                    .build();
        } catch (FailedToCreateException | SQLException e) {
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

}
