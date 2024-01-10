package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.AuthService;
import org.example.cli.Login;
import org.example.client.FailedToGetJobsException;
import org.example.client.FailedToVerifyTokenException;
import org.example.db.DatabaseConnector;
import org.example.exception.FailedToLoginException;
import org.example.exception.FailedTogenerateTokenException;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("DropWizard Auth API")
@Path("/api")
public class AuthController {
    private AuthService authService = new AuthService();

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login){
        try{
            return Response.ok(authService.login(login)).build();
        }
        catch (FailedTogenerateTokenException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToLoginException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/checkIsAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminStatus(String token){
        try {
            Boolean isAdmin = authService.isTokenAdmin(token);
            return Response.ok(isAdmin).build();
        } catch (SQLException | FailedToVerifyTokenException e) {
            throw new RuntimeException(e);
        }
    }

}
