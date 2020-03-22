package com.gamestoy.kitten;

import com.gamestoy.kitten.configuration.ServerConfiguration;
import com.gamestoy.kitten.context.ServerContext;
import com.gamestoy.kitten.exception.LifecycleException;
import com.gamestoy.kitten.filter.ServletFilterChainFactory;
import com.gamestoy.kitten.handler.ConnectionRejectedHandler;
import com.gamestoy.kitten.handler.SocketHandler;
import com.gamestoy.kitten.handler.exception.WriterException;
import com.gamestoy.kitten.handler.reader.RequestReader;
import com.gamestoy.kitten.handler.reader.RequestReaderFactory;
import com.gamestoy.kitten.handler.writer.ResponseWriter;
import com.gamestoy.kitten.handler.writer.ResponseWriterFactory;
import com.gamestoy.kitten.worker.WorkerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KittenServer {
  private final Logger logger = LoggerFactory.getLogger(KittenServer.class);
  private final ServerContext context;
  private final ServerConfiguration configuration;

  public KittenServer(ServerContext context, ServerConfiguration configuration) {
    this.context = context;
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
    var requestReader = createRequestReader();
    var responseWriter = createResponseWriter();
    var rejectHandler = new ConnectionRejectedHandler(responseWriter);
    var service = createExecutionService();
    var filterChainFactory = new ServletFilterChainFactory(configuration);
    logger.info("Server listening to port {}", server.getLocalPort());
    while (true) {
      try {
        var socket = server.accept();
        logger.debug("Connection created");
        var chain = filterChainFactory.createDefault(context);
        var worker = new SocketHandler(socket, requestReader, responseWriter, chain);
        try {
          service.execute(worker);
        } catch (RejectedExecutionException e) {
          rejectHandler.reject(socket);
          socket.close();
        }
      } catch (WriterException e) {
        logger.error("Error writing response", e);
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

  private ExecutorService createExecutionService() {
    return new WorkerFactory().create(configuration.getWorkers(), configuration.getKeepAlive());
  }
}
