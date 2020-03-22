package com.gamestoy.kitten.handler.reader;

import static java.util.stream.Collectors.mapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class HeadersParser {
  private final LineParser lineParser;

  public HeadersParser(LineParser lineParser) {
    this.lineParser = lineParser;
  }

  Map<String, List<String>> parse(InputStream inputStream) throws IOException {
    return getHeadersLines(inputStream).stream()
        .map(v -> v.split(": "))
        .collect(Collectors.groupingBy(v -> v[0], mapping(v -> v[1], Collectors.toList())));
  }

  private List<String> getHeadersLines(InputStream inputStream) throws IOException {
    List<String> headers = new LinkedList<>();
    String line;
    while (!(line = lineParser.readLine(inputStream)).equals("")) {
      headers.add(line);
    }
    return headers;
  }
}
