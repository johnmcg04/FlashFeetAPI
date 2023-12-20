package org.example.client;

public class JWTVerificationException extends Throwable{

    @Override
    public String getMessage() {
        return "JWT verification error";
    }
}
