package org.example.db;

import com.password4j.Hash;
import com.password4j.Password;
import org.example.cli.RegistrationDetails;
import org.example.util.DaoUtil;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDao {
    public static final int PASSWORD_SALT_LENGTH = 12;

    public boolean doesUsernameExist(Connection conn, String username) throws SQLException {
        String statement = "SELECT * FROM `User` WHERE Username = ?";
        ResultSet resultSet = DaoUtil.executeStatement(conn, statement, true, username);
        return resultSet.next();
    }

    public void register(Connection conn, RegistrationDetails registerDetails) throws SQLException {
        String insertStatement = "INSERT INTO `User` (Username, Password, RoleID) VALUES (?,?,?)";

        Hash hash = Password.hash(registerDetails.getPassword())
                .addRandomSalt(PASSWORD_SALT_LENGTH)
                .withArgon2();
        String password = hash.getResult();

        DaoUtil.executeStatement(conn, insertStatement, false,
                registerDetails.getUsername(), password, registerDetails.getRoleId());
    }
}
