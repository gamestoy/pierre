package com.gamestoy.kitten.handler.reader;

public class RequestReaderFactory {
  public RequestReader createHttpReader() {
    var lineParser = new LineParser();
    var urlParser = new URLParser();
    var bodyParser = new BodyParser();
    var methodParser = new MethodParser();
    var headersParser = new HeadersParser(lineParser);

    var requestParser =
        new RequestParser(methodParser, bodyParser, urlParser, headersParser, lineParser);
    return new HttpRequestReader(requestParser);
  }
}
