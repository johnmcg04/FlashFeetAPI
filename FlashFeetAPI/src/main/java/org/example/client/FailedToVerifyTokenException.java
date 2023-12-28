package org.example.client;

public class FailedToVerifyTokenException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to verify token";
    }
}
}
