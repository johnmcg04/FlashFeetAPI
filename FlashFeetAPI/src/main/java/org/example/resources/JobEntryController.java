package org.example.resources;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.api.JobEntryService;
import org.example.cli.JobEntryRequest;
import org.example.client.*;

import java.sql.SQLException;

@Api("FlashFeet Kainos Job Entry API")
@Path("/api")
public class JobEntryController {
    private JobEntryService jobEntryService = new JobEntryService();

    public JobEntryController() throws SQLException {
    }

    @GET
    @Path("/job-role-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobEntries(){
        try {
            return Response.ok(jobEntryService.getAllJobEntries()).build();
        } catch (FailedToGetJobEntriesException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/job-entry/{jobRole}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecificationByJobRole(@PathParam("jobRole") String jobRole) {
        try {
            return Response.ok(jobEntryService.getJobEntryByJobRole(jobRole)).build();
        } catch (FailedToGetJobEntriesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (JobEntryDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/create-job-entry")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobRole(JobEntryRequest jobEntry) {
        try {
            return Response.ok(jobEntryService.createJobEntry(jobEntry)).build();
        } catch (FailedToCreateJobEntryException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidJobEntryException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("/job-entry/{jobRole}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJobEntry(@PathParam("jobRole") String jobRole, JobEntryRequest jobEntry){
        try {
            jobEntryService.updateJobEntry(jobRole, jobEntry);
            return Response.ok().build();
        } catch (InvalidJobEntryException | JobEntryDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateJobEntryException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

}
