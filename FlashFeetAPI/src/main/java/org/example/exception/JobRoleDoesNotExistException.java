package org.example.exception;

public class JobRoleDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "Job role does not exist";
    }
}