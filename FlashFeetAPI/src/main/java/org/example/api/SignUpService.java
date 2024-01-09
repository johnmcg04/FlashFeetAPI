package org.example.api;


import org.example.cli.SignUp;
import org.example.client.FailedToVerifyTokenException;
import org.example.db.SignUpDao;
import org.example.db.DatabaseConnector;
import org.example.exception.FailedToLoginException;
import org.example.exception.FailedTogenerateTokenException;

import java.sql.Connection;
import java.sql.SQLException;

public class SignUpService {
    private SignUpDao authDao = new SignUpDao();
    private static DatabaseConnector databaseConnector = new DatabaseConnector();
    static Connection c = databaseConnector.getConnection();

    public SignUpService(){

    }

    public SignUpService(SignUpDao authDao, DatabaseConnector databaseConnector) {

    }

    public static int signUpUser(SignUp signUp) throws FailedToLoginException, FailedTogenerateTokenException {
        //call hashing and salting method here
        return SignUpDao.signUpUser(signUp, c); //returns -1 if failed to insert else 1 if valid insert
    }


}
