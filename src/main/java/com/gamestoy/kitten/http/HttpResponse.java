package com.gamestoy.kitten.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpResponse {
  private HttpStatus status;
  private Map<String, List<String>> headers;
  private Optional<String> body;

  public HttpResponse(HttpStatus status, Map<String, List<String>> headers, Optional<String> body) {
    this.status = status;
    this.headers = new HashMap<>(headers);
    this.body = body;
  }

  public HttpResponse(HttpStatus status) {
    this.status = status;
    this.headers = Map.of();
    this.body = Optional.empty();
  }

  public HttpStatus getStatus() {
    return status;
  }

  public Optional<String> getBody() {
    return body;
  }

  public Map<String, List<String>> getHeaders() {
    return headers;
  }

  public HttpResponse addHeader(String name, List<String> value) {
    headers.put(name, value);
    return this;
  }
}
