package org.example.client;

public class JobEntryDoesNotExistException extends Throwable{
    @Override
    public String getMessage(){
        return "There is no job entry for this job role";
    }
}
