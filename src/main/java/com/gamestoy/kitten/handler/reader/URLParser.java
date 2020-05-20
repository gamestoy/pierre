package com.gamestoy.kitten.handler.reader;

import com.gamestoy.kitten.handler.exception.InvalidRequestException;
import com.gamestoy.kitten.http.HttpHeaders;
import com.gamestoy.kitten.http.Protocol;
import com.gamestoy.kitten.http.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class URLParser {

  URL parse(String line, Map<String, List<String>> headers) throws InvalidRequestException {
    var words = line.split(" ");
    if (words.length > 0) {
      var splittedQuery = words[1].split("\\?");
      Optional<String> query = getQueryString(splittedQuery);
      var path = splittedQuery[0];
      var host =
          Optional.ofNullable(headers.get(HttpHeaders.HOST))
              .flatMap(l -> l.stream().findFirst())
              .orElse("localhost:9290");
      return new URL(Protocol.HTTP, host, path, query);
    } else {
      throw new InvalidRequestException("Error parsing url");
    }
  }

  private Optional<String> getQueryString(String[] splittedQuery) {
    return Optional.ofNullable(splittedQuery).filter(sq -> sq.length > 1).map(sq -> sq[1]);
  }
}
