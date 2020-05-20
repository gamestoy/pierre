package com.gamestoy.kitten.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpRequest {
  private HttpMethod method;
  private URL url;
  private Map<String, List<String>> headers;
  private Optional<String> body;
  private String remoteAddr;

  public HttpRequest(
      HttpMethod method,
      URL url,
      Map<String, List<String>> headers,
      Optional<String> body,
      String remoteAddr) {
    this.method = method;
    this.url = url;
    this.headers = new HashMap<>(headers);
    this.body = body;
    this.remoteAddr = remoteAddr;
  }

  public HttpRequest(
      HttpMethod method, URL url, Map<String, List<String>> headers, Optional<String> body) {
    this.method = method;
    this.url = url;
    this.headers = new HashMap<>(headers);
    this.body = body;
  }

  public HttpMethod getMethod() {
    return method;
  }

  public URL getUrl() {
    return url;
  }

  public Map<String, List<String>> getHeaders() {
    return headers;
  }

  public Optional<String> getBody() {
    return body;
  }

  public String getRemoteAddr() {
    return remoteAddr;
  }

  public void setRemoteAddr(String remoteAddr) {
    this.remoteAddr = remoteAddr;
  }

  public HttpRequest addHeader(String name, List<String> value) {
    this.headers.put(name, value);
    return this;
  }
}
