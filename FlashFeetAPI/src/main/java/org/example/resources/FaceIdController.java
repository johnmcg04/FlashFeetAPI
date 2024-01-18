package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.FaceIdService;
import org.example.cli.SignUp;
import org.example.exception.FailedTogenerateTokenException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("FlashFeet Face Id Controller")
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

    @POST
    @Path("/checkIfUserHasFaceIdLinked/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkIfUserHasFaceIdByUsername(@PathParam("username") String username) {
            return FaceIdService.checkIfUserHasFaceIdByUsername(username);
    }

}
