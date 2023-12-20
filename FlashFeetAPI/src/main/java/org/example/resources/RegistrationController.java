package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.RegistrationService;
import org.example.cli.RegistrationDetails;
import org.example.client.*;
import org.example.db.DatabaseConnector;
import org.example.db.RegistrationDao;
import org.example.util.JWTUtil;
import org.example.validator.RegistrationValidator;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("FlashFeet Registration API")
@Path("/api")
public class RegistrationController {
    private final RegistrationService registerService = new RegistrationService(
            new RegistrationDao(), new DatabaseConnector(), new RegistrationValidator());

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("Authorisation") String authHeader,
                             RegistrationDetails registerDetails) throws JWTExpiredException {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid or missing Authorisation header").build();
        }

        String jwt = authHeader.substring("Bearer ".length());
        if (!JWTUtil.isAdmin(jwt)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        try {
            registerService.register(registerDetails);
            return Response.ok().build();
        } catch (RegisterUsernameAlreadyExistsException | InvalidRegisterException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToRegisterException | SQLException e) {
            return Response.serverError().build();
        }
    }
}
