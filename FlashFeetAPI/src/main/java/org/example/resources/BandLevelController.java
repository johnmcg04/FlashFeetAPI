package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.BandLevelService;
import org.example.api.CapabilityService;
import org.example.client.FailedToGetBandLevelsException;
import org.example.client.FailedToGetCapabilitiesException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("FlashFeet Kainos Job Band Level API")
@Path("/api")
public class BandLevelController {
    private BandLevelService bandLevelService = new BandLevelService();

    @GET
    @Path("/bandlevel-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBandLevel(){
        try {
            return Response.ok(bandLevelService.getAllBandLevels()).build();
        } catch (FailedToGetBandLevelsException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
