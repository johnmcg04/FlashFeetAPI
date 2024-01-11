package org.example.exception;

public class FailedToSignUpException extends Exception {

    public FailedToSignUpException() {
        super("Failed to sign up. The provided information does not meet the required criteria.");
    }
}
