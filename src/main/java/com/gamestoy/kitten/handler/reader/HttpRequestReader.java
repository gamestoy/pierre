package com.gamestoy.kitten.handler.reader;

import com.gamestoy.kitten.handler.exception.InvalidRequestException;
import com.gamestoy.kitten.handler.exception.ReaderException;
import com.gamestoy.kitten.http.HttpRequest;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.Socket;

public class HttpRequestReader implements RequestReader {
  private final RequestParser parser;

  public HttpRequestReader(RequestParser parser) {
    this.parser = parser;
  }

  public HttpRequest read(Socket socket) throws InvalidRequestException, ReaderException {
    try {
      var inBuffer = new PushbackInputStream(socket.getInputStream());
      return parser.parse(inBuffer);
    } catch (IOException e) {
      throw new ReaderException("Error reading from socket", e);
    }
  }
}
