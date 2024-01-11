package org.example.api.integration;

import org.example.cli.Login;
import org.example.trueApplication;
import org.junit.jupiter.api.DisplayName;
import org.example.trueConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        Login invalidLogin = new Login("User123!", "User123!");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus()); //200 code returned
    }

    /**
     * All Tests if an invalid login returns a bad response.
     */
    @Test
    @DisplayName("Invalid Login should return a bad response")
    public void authLogin_ShouldThrowError_WhenPasswordDoesNotContainDigit() {
        Login invalidLogin = new Login("ValidUserName", "InvalidPassword!");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus()); //500 code returned
    }

    @Test
    @DisplayName("Invalid Login should return a bad response")
    public void authLogin_ShouldThrowError_WhenPasswordDoesNotContainLowerCaseLetter() {
        Login invalidLogin = new Login("ValidUserName", "INVALIDPASSWORD!123");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus()); //500 code returned
    }

    @Test
    @DisplayName("Invalid Login should return a bad response")
    public void authLogin_ShouldThrowError_WhenPasswordDoesNotUppercaseLetter() {
        Login invalidLogin = new Login("ValidUserName", "invalidpassword123!");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus()); //500 code returned
    }

    @Test
    @DisplayName("Invalid Login should return a bad response")
    public void authLogin_ShouldThrowError_WhenPasswordDoesNotContainSpecialCharacter() {
        Login invalidLogin = new Login("ValidUserName", "InvalidPassword123");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus()); //500 code returned
    }

    @Test
    @DisplayName("Invalid Login should return a bad response")
    public void authLogin_ShouldThrowError_WhenPasswordDoesNotHaveAMinimumLengthOfCharacters() {
        Login invalidLogin = new Login("ValidUserName", "Pword1!");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus()); //500 code returned
    }


}

