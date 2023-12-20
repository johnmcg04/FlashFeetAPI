package org.example.client;

public class InvalidRegisterException extends Throwable {

    @Override
    public String getMessage() {
        return "Invalid Registration";
    }
}