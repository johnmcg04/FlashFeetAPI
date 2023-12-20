package org.example.client;

public class RegisterUsernameAlreadyExistsException extends Throwable {

    @Override
    public String getMessage() {
        return "Registered Username Already Exists";
    }
}
