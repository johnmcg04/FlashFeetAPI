package org.example.client;

public class FailedToUpdateJobEntryException extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to update job entry";
    }
}
