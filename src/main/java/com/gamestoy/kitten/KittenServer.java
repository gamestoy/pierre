package com.gamestoy.kitten;

import com.gamestoy.kitten.configuration.ServerConfiguration;
import com.gamestoy.kitten.exception.LifecycleException;
import com.gamestoy.kitten.handler.SocketHandler;
import com.gamestoy.kitten.handler.reader.RequestReader;
import com.gamestoy.kitten.handler.reader.RequestReaderFactory;
import com.gamestoy.kitten.handler.writer.ResponseWriter;
import com.gamestoy.kitten.handler.writer.ResponseWriterFactory;
import java.io.IOException;
import java.net.ServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KittenServer {
  private final Logger logger = LoggerFactory.getLogger(KittenServer.class);
  private final ServerConfiguration configuration;

  public KittenServer(ServerConfiguration configuration) {
    this.configuration = configuration;
  }

  public void start() throws LifecycleException {
    try {
      var server = new ServerSocket(configuration.getPort(), configuration.getConnections(), null);
      start(server);
    } catch (IOException e) {
      throw new LifecycleException("Error creating server", e);
    }
  }

  private void start(ServerSocket server) {
    logger.info("Server listening on port {}", server.getLocalPort());
    var requestReader = createRequestReader();
    var responseWriter = createResponseWriter();
    while (true) {
      try {
        var socket = server.accept();
        logger.debug("Connection created");
        var worker = new SocketHandler(socket, requestReader, responseWriter);
        worker.run();
      } catch (IOException e) {
        logger.error("Error listening to connection", e);
      }
    }
  }

  private ResponseWriter createResponseWriter() {
    return new ResponseWriterFactory().createHttpWriter();
  }

  private RequestReader createRequestReader() {
    return new RequestReaderFactory().createHttpReader();
  }
}
