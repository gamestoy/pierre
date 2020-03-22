package com.gamestoy.kitten.handler.reader;

import com.gamestoy.kitten.http.HttpHeaders;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class BodyParser {

  Optional<String> parse(InputStream in, Map<String, List<String>> headers) {
    var contentLength =
        Optional.ofNullable(headers.get(HttpHeaders.CONTENT_LENGTH))
            .flatMap(l -> l.stream().findFirst())
            .map(Integer::valueOf)
            .orElse(0);
    if (contentLength != 0) {
      return readBody(in, contentLength);
    } else {
      return Optional.empty();
    }
  }

  private Optional<String> readBody(InputStream in, int length) {
    try {
      var body = new byte[length];
      in.read(body);
      return Optional.of(new String(body));
    } catch (Throwable e) {
      return Optional.empty();
    }
  }
}
