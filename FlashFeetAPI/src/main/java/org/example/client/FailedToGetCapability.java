package org.example.client;

public class FailedToGetCapability extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to get job capability";
    }
}
