package org.example.resources;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.example.api.JobRoleService;
import org.example.client.FailedToDeleteJobRoleException;
import org.example.client.FailedToGetJobsException;
import org.example.client.JobRoleDoesNotExistException;

import org.example.db.DatabaseConnector;

import java.sql.SQLException;


@Api("FlashFeet Kainos Job Data API")
@Path("/api")
public class JobRoleController {
    private JobRoleService jobRoleService = new JobRoleService();

    DatabaseConnector databaseConnector = new DatabaseConnector();

    @GET
    @Path("/job-role-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles(){
        try {
            return Response.ok(jobRoleService.getAllJobRoles()).build();
        } catch (FailedToGetJobsException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @DELETE
    @Path("/delete-job-role/{jobRole}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRole(@PathParam("jobRole") String jobRole) {
        try {
            jobRoleService.deleteJobRole(jobRole);

            return Response.ok().build();
        } catch (JobRoleDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (FailedToDeleteJobRoleException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
