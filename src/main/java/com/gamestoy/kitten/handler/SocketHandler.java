package com.gamestoy.kitten.handler;

import com.gamestoy.jdk.Servlet;
import com.gamestoy.kitten.handler.exception.InvalidRequestException;
import com.gamestoy.kitten.handler.exception.ReaderException;
import com.gamestoy.kitten.handler.exception.WriterException;
import com.gamestoy.kitten.handler.reader.RequestReader;
import com.gamestoy.kitten.handler.writer.ResponseWriter;
import com.gamestoy.kitten.http.HttpResponse;
import com.gamestoy.kitten.http.Responses;
import java.io.IOException;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketHandler implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
  private final RequestReader requestReader;
  private final ResponseWriter responseWriter;
  private final Socket socket;
  private final Servlet dispatcher;

  public SocketHandler(
      Socket socket,
      RequestReader requestReader,
      ResponseWriter responseWriter,
      Servlet dispatcher) {
    this.socket = socket;
    this.requestReader = requestReader;
    this.responseWriter = responseWriter;
    this.dispatcher = dispatcher;
  }

  @Override
  public void run() {
    try {
      try {
        var request = this.requestReader.read(socket);
        var response = new HttpResponse();
        this.dispatcher.dispatch(request, response);
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
      } finally {
        socket.close();
      }
    } catch (WriterException | IOException e) {
      logger.error("Error writing socket", e);
    }
  }

  private void handleUnexpectedError() throws WriterException {
    this.responseWriter.write(socket, Responses.INTERNAL_SERVER_ERROR);
  }
}
