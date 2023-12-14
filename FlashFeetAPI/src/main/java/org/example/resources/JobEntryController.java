package org.example.resources;

import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.api.JobEntryService;
import org.example.client.FailedToGetJobEntriesException;
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

}
