package org.example.client;

public class FailedToGetBandLevelsException extends Exception {
    @Override
    public String getMessage(){return "Failed to get band levels from the database";}
}
