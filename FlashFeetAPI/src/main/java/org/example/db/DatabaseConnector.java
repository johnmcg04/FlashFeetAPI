package org.example.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

    public Connection getConnection(){
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
