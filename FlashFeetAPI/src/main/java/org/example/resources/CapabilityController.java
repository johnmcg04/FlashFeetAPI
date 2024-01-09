package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.CapabilityService;
import org.example.api.JobEntryService;
import org.example.cli.Capability;
import org.example.client.FailedToGetCapabilitiesException;
import org.example.client.FailedToGetJobEntriesException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api("FlashFeet Kainos Job Capability API")
@Path("/api")
public class CapabilityController {
    private CapabilityService capabilityService = new CapabilityService();

    @GET
    @Path("/capability-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilities(){
        try {
            return Response.ok(capabilityService.getAllCapabilities()).build();
        } catch (FailedToGetCapabilitiesException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
