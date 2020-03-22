package com.gamestoy.kitten.handler.writer;

import com.gamestoy.kitten.http.HttpResponse;
import com.gamestoy.kitten.http.HttpStatus;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseParser {

  public String parse(HttpResponse response) {
    var builder = new StringBuilder();
    builder.append(createStatus(response.getStatus()));
    builder.append(createHeaders(response.getHeaders()));
    response.getBody().ifPresent(b -> builder.append(createBody(b)));
    return builder.toString();
  }

  private String createStatus(HttpStatus status) {
    return String.format(
        "HTTP/1.1 %s %s%s", status.getCode(), status.getDescription(), System.lineSeparator());
  }

  private String createHeaders(Map<String, List<String>> headers) {
    return headers.entrySet().stream()
        .map(this::createHeader)
        .collect(Collectors.joining(System.lineSeparator()));
  }

  private String createHeader(Map.Entry<String, List<String>> header) {
    var values = String.join(",", header.getValue());
    return String.format("%s: %s", header.getKey(), values);
  }

  private String createBody(String body) {
    return System.lineSeparator() + System.lineSeparator() + body;
  }
}
