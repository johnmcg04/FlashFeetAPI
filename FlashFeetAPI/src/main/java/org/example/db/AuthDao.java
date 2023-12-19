package org.example.db;

import org.apache.commons.lang3.time.DateUtils;
import org.example.cli.Login;
import org.example.db.DatabaseConnector;
import org.example.exception.FailedTogenerateTokenException;

import java.sql.*;
import java.util.UUID;

import java.util.Date;

public class AuthDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection connection = databaseConnector.getConnection();

    public boolean validLogin(Login login){
        try{
            String qrySelectPWord = "SELECT Password FROM `User` WHERE Username = ?";
            PreparedStatement pst = connection.prepareStatement(qrySelectPWord);
            pst.setString(1, login.getUsername());

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                return rs.getString("Password").equals(login.getPassword());
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return false;
    }



    public String generateToken(String username) throws SQLException{

        String token = UUID.randomUUID().toString();
        Date expiry = DateUtils.addHours(new Date(), 8);

        try{//connection does not open in here
            String insertStatement = "INSERT INTO `Token` (Username, Token, Expiry) VALUES (?,?,?)";

            connection.isClosed();
            PreparedStatement st = connection.prepareStatement(insertStatement); //fails as conn is closed

            st.setString(1, username);
            st.setString(2, token);
            st.setTimestamp(3, new java.sql.Timestamp(expiry.getTime()));

            st.executeUpdate();

            return token;
        }
        catch(SQLException ex){
            ex.getMessage();
        }



        return null;
    }

}

