package org.example.db;

import org.example.exception.DatabaseConnectionException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

    public Connection getConnection() throws DatabaseConnectionException {
        String user;
        String password;
        String host;
        String database;

        if (conn != null) {
            return conn;
        }

        try (FileInputStream propsStream = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(propsStream);

            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");
            database = props.getProperty("name");

            if (user == null || password == null || host == null)
                throw new IllegalArgumentException(
                        "Environment variables not set.");

            conn = DriverManager.getConnection("jdbc:mysql://"
                    + host + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false", user, password);

            return conn;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("I will always run!");
        }
        return null;
    }
}
