package org.example.api;


import org.example.cli.Login;
import org.example.client.FailedToVerifyTokenException;
import org.example.db.AuthDao;
import org.example.db.DatabaseConnector;
import org.example.exception.DatabaseConnectionException;
import org.example.exception.FailedToLoginException;
import org.example.exception.FailedTogenerateTokenException;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao = new AuthDao();
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection c = databaseConnector.getConnection();

    public AuthService() throws DatabaseConnectionException, SQLException {

    }

    public AuthService(AuthDao authDao, DatabaseConnector databaseConnector) throws DatabaseConnectionException, SQLException {

    }

    public String login(Login login) throws FailedToLoginException, FailedTogenerateTokenException {
        if(authDao.validLogin(login, c)){
            try{
                return authDao.generateToken(login.getUsername(), c);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        throw new FailedToLoginException();
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
