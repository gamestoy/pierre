package com.gamestoy.client;

import com.gamestoy.kitten.KittenServer;
import com.gamestoy.kitten.configuration.ServerConfiguration;
import com.gamestoy.kitten.context.ServerContext;
import com.gamestoy.kitten.exception.LifecycleException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quijote {
  private final Logger logger = LoggerFactory.getLogger(Quijote.class);

  public void run() {
    var configuration =
        new ServerConfiguration(
            9290, 200, 200, Duration.of(30, ChronoUnit.SECONDS), Set.of("1.1.1.1"));
    try {
      var servlet = new ApplicationServlet();
      var context = new ServerContext(servlet);
      context.addFilter(new FooFilter());
      context.addFilter(new BarFilter());
      new KittenServer(context, configuration).start();
    } catch (LifecycleException e) {
      logger.error("Error starting server", e);
    }
  }

  public static void main(String[] args) {
    new Quijote().run();
  }
}
