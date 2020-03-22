package com.gamestoy.kitten.http;

import com.gamestoy.jdk.ServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpResponse implements ServletResponse {
  private HttpStatus status;
  private final Map<String, List<String>> headers;
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

  @Override
  public int getStatusCode() {
    return this.status.getCode();
  }

  public Optional<String> getBody() {
    return body;
  }

  @Override
  public Optional<List<String>> getHeaderValues(String name) {
    return Optional.ofNullable(this.headers.get(name));
  }

  @Override
  public Optional<String> getHeader(String name) {
    return this.getHeaderValues(name).flatMap(v -> v.stream().findFirst());
  }

  @Override
  public void setStatusCode(int statusCode) {
    this.status = HttpStatus.getByCode(statusCode).orElse(HttpStatus.OK);
  }

  @Override
  public void addHeader(String name, String values) {}

  public Map<String, List<String>> getHeaders() {
    return headers;
  }

  @Override
  public void addHeaderValues(String name, List<String> value) {
    headers.put(name, value);
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public void setBody(Optional<String> body) {
    this.body = body;
  }
}
