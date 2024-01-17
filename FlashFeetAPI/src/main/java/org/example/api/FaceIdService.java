package org.example.api;

import org.example.cli.JobEntry;
import org.example.cli.JobEntryRequest;
import org.example.cli.SignUp;
import org.example.client.FailedToUpdateJobEntryException;
import org.example.client.InvalidJobEntryException;
import org.example.client.JobEntryDoesNotExistException;
import org.example.db.DatabaseConnector;
import org.example.db.FaceIdDao;
import org.example.db.SignUpDao;
import org.example.exception.FailedToSignUpException;
import org.example.exception.FailedTogenerateTokenException;

import java.sql.Connection;
import java.sql.SQLException;

import static org.example.api.SignUpService.validSignUpPassword;

public class FaceIdService {

    private SignUpDao signUpDao = new SignUpDao();
    private static DatabaseConnector databaseConnector = new DatabaseConnector();
    static Connection c;

    static {
        c = databaseConnector.getConnection();
    }

    public FaceIdService() throws SQLException {
    }

    public static boolean signUpUserWithFaceId(SignUp signUp) throws FailedTogenerateTokenException, Exception {
        //call hashing and salting method here
        try{
            //password validation here
            if(!validSignUpPassword(signUp.getPassword())){
                return false;
            }
            SignUp saltedSignUpDetails = SignUpService.SaltUsernameAndPassword(signUp);
            SignUp hashedSignUpDetails = SignUpService.HashPassword(saltedSignUpDetails);
            return FaceIdDao.signUpUserWithFaceId(hashedSignUpDetails, c); //returns false if failed to insert else true if valid insert
        }
        catch(FailedToSignUpException ex){
            ex.getMessage();
        }
        return false;
    }
}
