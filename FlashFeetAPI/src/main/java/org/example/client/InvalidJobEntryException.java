package org.example.client;

public class InvalidJobEntryException extends Throwable{
    public InvalidJobEntryException(String error) {
        super(error);
    }
}
