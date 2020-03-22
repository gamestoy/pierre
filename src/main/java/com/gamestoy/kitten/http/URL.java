package com.gamestoy.kitten.http;

import java.util.Optional;

public class URL {
  private final Protocol protocol;
  private final String host;
  private final String path;
  private final Optional<String> queryString;

  public URL(Protocol protocol, String host, String path, Optional<String> queryString) {
    this.protocol = protocol;
    this.host = host;
    this.path = path;
    this.queryString = queryString;
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public String getHost() {
    return host;
  }

  public String getPath() {
    return path;
  }

  public Optional<String> getQueryString() {
    return queryString;
  }

  @Override
  public String toString() {
    return String.format(
        "%s://%s%s%s",
        this.protocol.toString().toLowerCase(), this.host, this.path, this.queryString.orElse(""));
  }
}
