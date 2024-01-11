package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.SignUpService;
import org.example.cli.SignUp;
import org.example.client.FailedToGetJobEntriesException;
import org.example.exception.FailedTogenerateTokenException;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("DropWizard Auth API")
@Path("/api")
public class SignUpController {

    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    public Response SignUp(SignUp signUp){
        try{
            return Response.ok(SignUpService.signUpUser(signUp)).build();
        }
        catch (FailedTogenerateTokenException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
