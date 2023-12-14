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

@Api("FlashFeet Kainos Job Data API")
@Path("/api")
public class JobRoleController {
    private JobRoleService jobRoleService = new JobRoleService();

    @GET
    @Path("/job-role-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles(){
        System.out.println("Test");
        try {
            return Response.ok(jobRoleService.getAllJobRoles()).build();
        } catch (FailedToGetJobsException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
    @DELETE
    @Path("/delete-job-role/{jobRole}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRole(@PathParam("jobRole") String jobRole) {
        System.out.println(jobRole);
        try {
            jobRoleService.deleteJobRole(jobRole);

            return Response.ok().build();
        } catch (JobRoleDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteJobRoleException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
