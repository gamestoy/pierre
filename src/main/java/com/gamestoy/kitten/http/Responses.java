package com.gamestoy.kitten.http;

import java.util.Map;
import java.util.Optional;

public class Responses {
  public static final HttpResponse BAD_REQUEST =
      new HttpResponse(HttpStatus.BAD_REQUEST, Map.of(), Optional.empty());
  public static final HttpResponse NOT_FOUND =
      new HttpResponse(HttpStatus.NOT_FOUND, Map.of(), Optional.empty());
  public static final HttpResponse INTERNAL_SERVER_ERROR =
      new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, Map.of(), Optional.empty());
  public static final HttpResponse SERVICE_UNAVAILABLE =
      new HttpResponse(HttpStatus.SERVICE_UNAVAILABLE, Map.of(), Optional.empty());
}
