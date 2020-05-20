package com.gamestoy.kitten.handler;

import com.gamestoy.kitten.handler.exception.WriterException;
import com.gamestoy.kitten.handler.writer.ResponseWriter;
import com.gamestoy.kitten.http.Responses;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionRejectedHandler {
  private final Logger logger = LoggerFactory.getLogger(ConnectionRejectedHandler.class);
  private final ResponseWriter responseWriter;

  public ConnectionRejectedHandler(ResponseWriter responseWriter) {
    this.responseWriter = responseWriter;
  }

  public void reject(Socket socket) throws WriterException {
    logger.debug("Connection rejected");
    responseWriter.write(socket, Responses.SERVICE_UNAVAILABLE);
  }
}
