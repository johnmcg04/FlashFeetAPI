package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.FaceIdService;
import org.example.api.SignUpService;
import org.example.cli.JobEntryRequest;
import org.example.cli.SignUp;
import org.example.client.FailedToUpdateJobEntryException;
import org.example.client.InvalidJobEntryException;
import org.example.client.JobEntryDoesNotExistException;
import org.example.exception.FailedTogenerateTokenException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("FlashFeet Kainos Job Entry API")
@Path("/api")
public class FaceIdController {

    @POST
    @Path("/signupWithFaceId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response SignUp(SignUp signUp){
        try{
            return Response.ok(FaceIdService.signUpUserWithFaceId(signUp)).build();
        }
        catch (FailedTogenerateTokenException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
