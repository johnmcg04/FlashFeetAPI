package org.example.resources;

import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.api.JobRoleService;
import org.example.client.FailedToGetCapability;
import org.example.client.FailedToGetJobsException;

@Api("FlashFeet Kainos Job Data API")
@Path("/api")
public class JobRoleController {
    private JobRoleService jobRoleService = new JobRoleService();

    @GET
    @Path("/job-role-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles(){
        try {
            return Response.ok(jobRoleService.getAllJobRoles()).build();
        } catch (FailedToGetJobsException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/capabilities-list") //used for drop down list of capabilities
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilities(){
        try {
            return Response.ok(jobRoleService.getAllCapabilities()).build();
        } catch (FailedToGetCapability e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

}
