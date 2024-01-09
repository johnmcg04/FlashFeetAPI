package org.example.client;

public class FailedToCreateJobEntryException extends Throwable {
    @Override
    public String getMessage(){return "Failed to create Job Role";}
}
