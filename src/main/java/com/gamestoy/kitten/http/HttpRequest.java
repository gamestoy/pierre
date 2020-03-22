package com.gamestoy.kitten.http;

import com.gamestoy.jdk.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpRequest implements ServletRequest {

  private final HttpMethod method;
  private final URL url;
  private final Map<String, List<String>> headers;
  private final Optional<String> body;
  private String remoteAddress;

  public HttpRequest(
      HttpMethod method,
      URL url,
      Map<String, List<String>> headers,
      Optional<String> body,
      String remoteAddress) {
    this.method = method;
    this.url = url;
    this.headers = new HashMap<>(headers);
    this.body = body;
    this.remoteAddress = remoteAddress;
  }

  public HttpRequest(
      HttpMethod method, URL url, Map<String, List<String>> headers, Optional<String> body) {
    this.method = method;
    this.url = url;
    this.headers = new HashMap<>(headers);
    this.body = body;
  }

  @Override
  public String getHost() {
    return this.url.getHost();
  }

  @Override
  public String getMethod() {
    return this.method.name();
  }

  public HttpMethod getHttpMethod() {
    return method;
  }

  @Override
  public Optional<List<String>> getHeaderValues(String name) {
    return Optional.ofNullable(this.headers.get(name));
  }

  @Override
  public Optional<String> getHeader(String name) {
    return getHeaderValues(name).flatMap(v -> v.stream().findFirst());
  }

  @Override
  public String getPath() {
    return this.url.getPath();
  }

  public URL getUrl() {
    return url;
  }

  @Override
  public Map<String, List<String>> getHeaders() {
    return headers;
  }

  @Override
  public Optional<String> getBody() {
    return body;
  }

  @Override
  public Optional<String> getQueryString() {
    return Optional.empty();
  }

  @Override
  public String getRemoteAddress() {
    return remoteAddress;
  }

  @Override
  public void setRemoteAddress(String remoteAddress) {
    this.remoteAddress = remoteAddress;
  }

  public HttpRequest addHeader(String name, List<String> value) {
    this.headers.put(name, value);
    return this;
  }
}
