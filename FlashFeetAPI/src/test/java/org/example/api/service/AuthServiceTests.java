package org.example.api.service;

import org.example.api.SignUpService;
import org.example.cli.Login;
import org.example.db.SignUpDao;
import org.example.db.DatabaseConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    SignUpDao authDao = mock(SignUpDao.class);
    DatabaseConnector databaseConnector = mock(DatabaseConnector.class);

    SignUpService authService = new SignUpService(authDao, databaseConnector);

    private final Connection c = mock(Connection.class);

    public AuthServiceTests() throws SQLException {
    }



}







