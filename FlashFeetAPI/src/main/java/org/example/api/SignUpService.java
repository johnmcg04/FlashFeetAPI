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
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection c = databaseConnector.getConnection();

    public SignUpService(){

    }

    public SignUpService(SignUpDao authDao, DatabaseConnector databaseConnector) {

    }

    public static int signUpUser(SignUp signUp) throws FailedToLoginException, FailedTogenerateTokenException {
        //call hashing and salting method here
        return SignUpDao.signUpUser(signUp, c); //returns -1 if failed to insert else 1 if valid insert
    }

    public boolean isTokenAdmin(String token) throws SQLException, FailedToVerifyTokenException {
        try{
            int roleId = authDao.getRoleIdFromToken(token);

            if(roleId == 1){
                return true; //token is an admin
            }
        }
        catch(SQLException ex){
            throw new FailedToVerifyTokenException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
