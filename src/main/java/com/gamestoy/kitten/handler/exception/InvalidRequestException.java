package com.gamestoy.kitten.handler.exception;

public class InvalidRequestException extends Exception {
  public InvalidRequestException(String message) {
    super("Invalid Request: " + message);
  }

  public InvalidRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
