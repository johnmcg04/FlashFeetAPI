package org.example.db;

import org.apache.commons.lang3.time.DateUtils;
import org.example.api.SignUpService;
import org.example.cli.Login;
import org.example.cli.SignUp;

import java.sql.*;
import java.util.UUID;

import java.util.Date;

public class SignUpDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection connection = databaseConnector.getConnection();

    public static int signUpUser(SignUp hashedSignUpDetails, Connection c) {
        try {
//            SignUp saltedSignUpDetails = SignUpService.SaltUsernameAndPassword(signUp);
//            SignUp hashedSignUpDetails = SignUpService.HashPassword(saltedSignUpDetails);

            String qryInsertSignUpDetails = "INSERT INTO `User` (Username, Password, Salt, RoleID) VALUES(?,?,?,2);";
            PreparedStatement preparedStatement = c.prepareStatement(qryInsertSignUpDetails);
            preparedStatement.setString(1, hashedSignUpDetails.getUsername());
            preparedStatement.setString(2, hashedSignUpDetails.getPassword());
            preparedStatement.setString(3, hashedSignUpDetails.getSalt());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }


}

