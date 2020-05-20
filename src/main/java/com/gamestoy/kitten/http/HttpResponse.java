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

  public HttpResponse() {
    this.status = HttpStatus.OK;
    this.headers = new HashMap<>();
    this.body = Optional.empty();
  }

  public HttpResponse(HttpStatus status) {
    this.status = status;
    this.headers = new HashMap<>();
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

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public void setBody(Optional<String> body) {
    this.body = body;
  }
}
