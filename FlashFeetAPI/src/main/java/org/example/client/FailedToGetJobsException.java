package org.example.client;

public class FailedToGetJobsException extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to get job roles from the database";
    }
}
