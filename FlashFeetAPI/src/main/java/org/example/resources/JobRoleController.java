package org.example.resources;

import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.api.JobRoleService;
import org.example.cli.JobRole;
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
    @Path("/capability-by-job-role/{job_role}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilityByJobRole(@PathParam("job_role") String jobRole){
        try {
            return Response.ok(jobRoleService.getCapabilityByJobRole(jobRole)).build();
        } catch (FailedToGetJobsException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
