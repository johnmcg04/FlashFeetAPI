package org.example.resources;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.api.JobEntryService;
import org.example.cli.JobEntryRequest;
import org.example.client.FailedToGetJobEntriesException;
import org.example.client.FailedToUpdateJobEntryException;
import org.example.client.InvalidJobEntryException;
import org.example.client.JobEntryDoesNotExistException;

@Api("FlashFeet Kainos Job Data API")
@Path("/api")
public class JobEntryController {
    private JobEntryService jobEntryService = new JobEntryService();

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
