package org.example.db;

import org.apache.commons.lang3.time.DateUtils;
import org.example.cli.Login;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Base64;
import java.util.UUID;

import java.util.Date;

public class AuthDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection connection = databaseConnector.getConnection();

    public boolean validLogin(Login login, Connection c){
        try{
            String qrySelectPWord = "SELECT Password, Salt FROM `User` WHERE Username = ?";
            PreparedStatement pst = connection.prepareStatement(qrySelectPWord);
            pst.setString(1, login.getUsername());

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                String salt = rs.getString("Salt");
                String storedHash = rs.getString("Password");
                String hashedPassword = HashUsernameAndPassword(login.getPassword(), salt);

                return storedHash.equals(hashedPassword);
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String generateToken(String username, Connection c) throws SQLException{

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

    public boolean isAdmin(String username, Connection c){
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
                " WHERE Token = '" + token + "';";

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

    public static String HashUsernameAndPassword(String password, String salt) throws Exception {
        int iterations = 65536;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);

        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
        SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hashedPassword = key.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}

