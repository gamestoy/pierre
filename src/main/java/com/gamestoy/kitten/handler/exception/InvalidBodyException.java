package com.gamestoy.kitten.handler.exception;

public class InvalidBodyException extends ParserException {
  public InvalidBodyException() {
    super("Error while parsing body");
  }
}
