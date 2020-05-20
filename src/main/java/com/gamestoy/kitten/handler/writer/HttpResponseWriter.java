package com.gamestoy.kitten.handler.writer;

import com.gamestoy.kitten.handler.exception.WriterException;
import com.gamestoy.kitten.http.HttpResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpResponseWriter implements ResponseWriter {
  private final ResponseParser parser;

  public HttpResponseWriter(ResponseParser parser) {
    this.parser = parser;
  }

  @Override
  public void write(Socket socket, HttpResponse httpResponse) throws WriterException {
    try {
    var response = parser.parse(httpResponse);
    write(socket.getOutputStream(), response);
    } catch (IOException e) {
      throw new WriterException("Error writing socket", e);
    }
  }

  private void write(OutputStream out, String response) {
    var outBuffer = new PrintWriter(out, true);
    outBuffer.println(response);
  }
}
