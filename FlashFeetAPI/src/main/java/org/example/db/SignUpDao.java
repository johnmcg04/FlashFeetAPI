package org.example.db;

import org.apache.commons.lang3.time.DateUtils;
import org.example.cli.Login;
import org.example.cli.SignUp;

import java.sql.*;
import java.util.UUID;

import java.util.Date;

public class SignUpDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection connection = databaseConnector.getConnection();

    public static int signUpUser(SignUp signUp, Connection c) {
        try {
            String qryInsertSignUpDetails = "INSERT INTO `User` (Username, Password, RoleID) VALUES(?,?,2);";
            PreparedStatement preparedStatement = c.prepareStatement(qryInsertSignUpDetails);
            preparedStatement.setString(1, signUp.getUsername());
            preparedStatement.setString(2, signUp.getPassword());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return 1;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

}

