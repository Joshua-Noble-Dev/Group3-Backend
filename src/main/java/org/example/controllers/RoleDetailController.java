package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FormatException;
import org.example.exceptions.InvalidException;
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
    public Response getRoleInformation(
            final @PathParam("detailId") String detailId) {
        try {
            return Response.ok().entity(roleDetailService.getRoleInformation(
                    detailId)).build();
        } catch (FormatException e) {
            return Response.status(
                    Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InvalidException e) {
            return Response.status(
                    Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (DoesNotExistException e) {
            return Response.status(
                    Response.Status.NOT_FOUND).entity(
                            e.getMessage()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }
}
