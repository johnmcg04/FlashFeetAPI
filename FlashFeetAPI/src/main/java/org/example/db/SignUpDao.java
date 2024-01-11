package org.example.db;
import org.example.cli.SignUp;

import java.sql.*;

public class SignUpDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection connection = databaseConnector.getConnection();

    public SignUpDao() throws SQLException {
    }

    public static boolean signUpUser(SignUp hashedSignUpDetails, Connection c) {
        try {
            String qryInsertSignUpDetails = "INSERT INTO `User` (Username, Password, Salt, RoleID) VALUES(?,?,?,2);";
            PreparedStatement preparedStatement = c.prepareStatement(qryInsertSignUpDetails);
            preparedStatement.setString(1, hashedSignUpDetails.getUsername());
            preparedStatement.setString(2, hashedSignUpDetails.getPassword());
            preparedStatement.setString(3, hashedSignUpDetails.getSalt());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


}

