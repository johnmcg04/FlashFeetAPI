package org.example.api.integration;

import org.example.api.AuthService;
import org.example.cli.Login;
import org.example.db.AuthDao;
import org.example.db.DatabaseConnector;
import org.example.exception.FailedToLoginException;
import org.example.exception.FailedTogenerateTokenException;
import org.example.trueApplication;
import org.junit.jupiter.api.DisplayName;
import org.example.trueConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.example.cli.Login;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains integration tests for the authentication feature.
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {

    /**
     * The application instance used for testing.
     */
    static final DropwizardAppExtension<trueConfiguration> APP = new DropwizardAppExtension<>(
            trueApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    /**
     * Tests if a valid login returns an OK response.
     */
    @Test
    @DisplayName("Valid Login should return a OK response")
    public void authLogin_ShouldAllowLogin_WhenValidLoginDetailsEntered() {
        Login invalidLogin = new Login("user", "password");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus()); //200 code returned
    }

    /**
     * Tests if an invalid login returns a bad response.
     */
    @Test
    @DisplayName("Invalid Login should return a bad response")
    public void authLogin_ShouldThrowError_WhenInvalidLoginDetailsEntered() {
        Login invalidLogin = new Login("invalidUsername64hvhjvhjvhjvfg85fouydwygwej!!!weurvbi", "InvalidPasswordhjvrekhbrchjvcjlbn");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus()); //500 code returned
    }
}

