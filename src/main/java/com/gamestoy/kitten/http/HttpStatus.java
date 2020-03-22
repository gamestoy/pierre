package com.gamestoy.kitten.http;

import java.util.Arrays;
import java.util.Optional;

public enum HttpStatus {
  OK(200, "OK"),
  NO_CONTENT(204, "No Content"),
  BAD_REQUEST(400, "Bad Request"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not Found"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
  SERVICE_UNAVAILABLE(503, "Service Unavailable");

  private int code;
  private String description;

  HttpStatus(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static Optional<HttpStatus> getByCode(int code) {
    return Arrays.stream(values()).filter(v -> v.code == code).findFirst();
  }
}
