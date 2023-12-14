package org.example.resources;

import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.api.JobRoleService;
import org.example.client.FailedToGetJobsException;
import org.example.db.DatabaseConnector;

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
            DatabaseConnector databaseConnector = null;
            return Response.ok(jobRoleService.getAllJobRoles(databaseConnector)).build();
        } catch (FailedToGetJobsException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
