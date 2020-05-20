package com.gamestoy.kitten.handler;

import com.gamestoy.kitten.handler.exception.InvalidRequestException;
import com.gamestoy.kitten.handler.exception.ReaderException;
import com.gamestoy.kitten.handler.exception.WriterException;
import com.gamestoy.kitten.handler.reader.RequestReader;
import com.gamestoy.kitten.handler.writer.ResponseWriter;
import com.gamestoy.kitten.http.HttpHeaders;
import com.gamestoy.kitten.http.HttpResponse;
import com.gamestoy.kitten.http.HttpStatus;
import com.gamestoy.kitten.http.Responses;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketHandler implements Runnable {
  private final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
  private final RequestReader requestReader;
  private final ResponseWriter responseWriter;
  private final Socket socket;

  public SocketHandler(Socket socket, RequestReader requestReader, ResponseWriter responseWriter) {
    this.socket = socket;
    this.requestReader = requestReader;
    this.responseWriter = responseWriter;
  }

  @Override
  public void run() {
    try {
      try {
        var request = this.requestReader.read(socket);
        var response = createMockResponse();
        this.responseWriter.write(socket, response);
      } catch (InvalidRequestException e) {
        logger.error("Invalid request", e);
        this.responseWriter.write(socket, Responses.BAD_REQUEST);
      } catch (ReaderException e) {
        logger.error("Error reading socket", e);
        handleUnexpectedError();
      } catch (RuntimeException e) {
        logger.error("Unexpected error", e);
        handleUnexpectedError();
      }
    } catch (WriterException e) {
      logger.error("Error writing socket", e);
    }
  }

  private void handleUnexpectedError() throws WriterException {
    this.responseWriter.write(socket, Responses.INTERNAL_SERVER_ERROR);
  }

  private HttpResponse createMockResponse() {
    var body = "test";
    return new HttpResponse(
        HttpStatus.OK,
        Map.of(
            HttpHeaders.CONTENT_TYPE,
            List.of("text/plain"),
            HttpHeaders.CONTENT_LENGTH,
            List.of(String.valueOf(body.length()))),
        Optional.of(body));
  }
}
