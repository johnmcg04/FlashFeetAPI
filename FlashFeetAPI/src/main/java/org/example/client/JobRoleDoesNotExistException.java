package org.example.client;

public class JobRoleDoesNotExistException extends Throwable {
    @Override
    public String getMessage() { return "The job role specified does not exist.";}
}
