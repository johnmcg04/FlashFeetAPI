package org.example.db;

import org.example.cli.JobEntry;
import org.example.cli.JobEntryRequest;
import org.example.cli.SignUp;

import java.sql.*;
import java.util.Objects;

public class FaceIdDao {

    private static final DatabaseConnector databaseConnector = new DatabaseConnector();

    public static boolean signUpUserWithFaceId(SignUp hashedSignUpDetails, Connection c) {
        try {
            String qryInsertSignUpDetails = "INSERT INTO `User` (Username, Password, Salt, RoleID, hasFaceId) VALUES(?,?,?,2,1);";
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

    public static boolean checkIfUserHasFaceIdByUsername(String username) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery( "SELECT Username FROM User WHERE hasFaceId = 1");

        while (rs.next()){
            String databaseUsername = rs.getString("Username");
            if(Objects.equals(username, databaseUsername)){
                return true;
            }
        }
        return false;
    }
}
