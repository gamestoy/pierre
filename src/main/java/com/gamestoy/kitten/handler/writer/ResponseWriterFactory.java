package com.gamestoy.kitten.handler.writer;

public class ResponseWriterFactory {

  public ResponseWriter createHttpWriter() {
    var parser = new ResponseParser();
    return new HttpResponseWriter(parser);
  }

}
