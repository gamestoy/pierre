package com.gamestoy.kitten.handler.reader;

import com.gamestoy.kitten.handler.exception.InvalidRequestException;
import com.gamestoy.kitten.handler.exception.ParserException;
import com.gamestoy.kitten.http.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestParser {
  private final Logger logger = LoggerFactory.getLogger(RequestParser.class);
  private final MethodParser methodParser;
  private final BodyParser bodyParser;
  private final URLParser urlParser;
  private final HeadersParser headersParser;
  private final LineParser lineParser;

  public RequestParser(
      MethodParser methodParser,
      BodyParser bodyParser,
      URLParser urlParser,
      HeadersParser headersParser,
      LineParser lineParser) {
    this.methodParser = methodParser;
    this.bodyParser = bodyParser;
    this.urlParser = urlParser;
    this.headersParser = headersParser;
    this.lineParser = lineParser;
  }

  public HttpRequest parse(InputStream is) throws IOException, InvalidRequestException {
    try {
      var firstLine = lineParser.readLine(is);
      if (firstLine == null) {
        throw new InvalidRequestException("Invalid format");
      }
      var method = methodParser.parse(firstLine);

      var headers = headersParser.parse(is);

      var body = bodyParser.parse(is, headers);

      var url = urlParser.parse(firstLine, headers);

      logger.debug(
          String.format("Calling %s %s headers %s", method, url.toString(), headers.toString()));

      return new HttpRequest(method, url, headers, body);
    } catch (ParserException e) {
      throw new InvalidRequestException("Error parsing request", e);
    }
  }
}
