package org.example.exception;

public class DatabaseConnectionException extends Throwable {
    public DatabaseConnectionException(Exception e) {
        super(e);
    }
}