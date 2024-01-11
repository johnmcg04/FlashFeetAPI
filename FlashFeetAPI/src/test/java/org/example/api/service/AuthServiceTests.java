package org.example.api.service;

import org.example.api.AuthService;
import org.example.cli.Login;
import org.example.db.AuthDao;
import org.example.db.DatabaseConnector;
import org.example.exception.DatabaseConnectionException;
import org.example.exception.FailedToLoginException;
import org.example.exception.FailedTogenerateTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    AuthDao authDao = mock(AuthDao.class);
    DatabaseConnector databaseConnector = mock(DatabaseConnector.class);

    AuthService authService = new AuthService(authDao, databaseConnector);

    private final Connection c = mock(Connection.class);

    public AuthServiceTests() throws DatabaseConnectionException, SQLException {
    }

    @Test
    @DisplayName("Testing valid login details")
    public void login_ShouldReturnTrue_WhenValidLoginReturnsTrue() throws SQLException, FailedTogenerateTokenException, FailedToLoginException, DatabaseConnectionException {
        Login validLogin = new Login("User123!","User123!");
        when(databaseConnector.getConnection()).thenReturn(c);
        when(authDao.validLogin(validLogin, c)).thenReturn(Boolean.TRUE);
        if(authService.login(validLogin) instanceof String){ //returns a string i.e. returns the token
            assertTrue(true);
        }
        else fail();
    }

    @Test
    @DisplayName("Testing invalid login details")
    public void login_ShouldReturnException_WhenValidLoginReturnsFalse() throws SQLException, DatabaseConnectionException {
        Login inValidLogin = new Login("invalidLogin6484846!!!!547r76rybfhtfjjvgv","pwordhvgvjvggvvggvggvv-");
        when(databaseConnector.getConnection()).thenReturn(c);
        when(authDao.validLogin(inValidLogin, c)).thenReturn(Boolean.FALSE);
        assertThrows(FailedToLoginException.class, () -> authService.login(inValidLogin));
    }



}







