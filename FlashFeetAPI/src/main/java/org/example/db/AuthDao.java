package org.example.db;

import com.auth0.jwt.exceptions.TokenExpiredException;
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
            throw new SQLException();
        }
    }

    public boolean isAdmin(String username){
        int roleIdForAdmin = 1;
        int result = 0;
        try{
            String qrySelectUsername = "SELECT RoleID FROM `User` WHERE Username = ?";
            PreparedStatement pst = connection.prepareStatement(qrySelectUsername);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                result = rs.getInt("RoleID");
                if(result == roleIdForAdmin){
                    return true;
                }
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    public int getRoleIdFromToken(String token) throws Exception {
        Statement st = connection.createStatement();

        String sqlQuery = "SELECT RoleID, Expiry FROM `User` join `Token` using (Username)" +
                " WHERE Token = ?";
        PreparedStatement pst = connection.prepareStatement(sqlQuery);
        pst.setString(1, token);

        ResultSet rs = st.executeQuery(sqlQuery);

        while(rs.next()){
            Timestamp expiry = rs.getTimestamp("Expiry");

            if(expiry.after(new Date())){
                return rs.getInt("RoleID"); //return the role id of the users token
            }
            else{
                throw new Exception();
            }

        }
        return -1;
    }
}

