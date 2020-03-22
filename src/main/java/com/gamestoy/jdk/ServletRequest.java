package com.gamestoy.jdk;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServletRequest {

  String getRemoteAddress();

  void setRemoteAddress(String address);

  String getHost();

  String getMethod();

  Optional<String> getHeader(String name);

  Optional<List<String>> getHeaderValues(String name);

  String getPath();

  Optional<String> getBody();

  Optional<String> getQueryString();

  Map<String, List<String>> getHeaders();
}
