package org.example.client;

public class JWTExpiredException extends Throwable{

    @Override
    public String getMessage() {
        return "ERROR this JWT session has expired";
    }
}
