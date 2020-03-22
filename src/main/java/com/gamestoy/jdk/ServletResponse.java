package com.gamestoy.jdk;

import java.util.List;
import java.util.Optional;

public interface ServletResponse {

  int getStatusCode();

  Optional<String> getBody();

  Optional<List<String>> getHeaderValues(String name);

  Optional<String> getHeader(String name);

  void setStatusCode(int statusCode);

  void addHeaderValues(String name, List<String> values);

  void addHeader(String name, String values);

  void setBody(Optional<String> body);
}
