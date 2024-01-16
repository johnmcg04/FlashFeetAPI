package org.example.client;

public class FailedToDeleteJobEntryException extends Throwable {
  @Override
  public String getMessage() {return "Failed to delete Job Role" ;}
}