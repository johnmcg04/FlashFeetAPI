package org.example.api;


import org.example.cli.Login;
import org.example.client.FailedToVerifyTokenException;
import org.example.db.AuthDao;
import org.example.exception.FailedToLoginException;
import org.example.exception.FailedTogenerateTokenException;

import java.sql.SQLException;

public class AuthService {

    private AuthDao authDao = new AuthDao();

    public String login(Login login) throws FailedToLoginException, FailedTogenerateTokenException {

        if(authDao.validLogin(login)){
            try{

                return authDao.generateToken(login.getUsername());

            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        throw new FailedToLoginException();
    }

    public boolean chkAdminStatus(String username) throws FailedToLoginException, FailedTogenerateTokenException {
        boolean isAdmin = false;
        if(authDao.isAdmin(username)){
            return isAdmin = true;
        }
        else{ return isAdmin;}
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
