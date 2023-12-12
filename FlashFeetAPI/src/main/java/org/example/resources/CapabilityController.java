package org.example.resources;

import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.api.CapabilityService;
import org.example.client.FailedToGetCapability;

@Api("FlashFeet Kainos Job Data API")
@Path("/api")
public class CapabilityController {
    private CapabilityService capabilityService = new CapabilityService();

    @GET
    @Path("/capability-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilities(){
        try {
            return Response.ok(capabilityService.getAllCapabilities()).build();
        } catch (FailedToGetCapability e) {
            throw new RuntimeException(e);
        }
    }
}
