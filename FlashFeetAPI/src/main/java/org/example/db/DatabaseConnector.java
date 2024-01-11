package org.example.db;

import org.example.exception.DatabaseConnectionException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

    public Connection getConnection() {
        String user, password, host, name;

        if (conn != null) {
            return conn;
        }

        try (FileInputStream propsStream = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(propsStream);

            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");
            name = props.getProperty("name");

            if (user == null || password == null || host == null)
                throw new IllegalArgumentException("Properties file must exist " +
                        "and must contain user, password, name and host properties.");

            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);
            return conn;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("I will always run!");
        }
        return null;
    }
}
