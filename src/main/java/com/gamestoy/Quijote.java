package com.gamestoy;

import com.gamestoy.kitten.KittenServer;
import com.gamestoy.kitten.configuration.ServerConfiguration;
import com.gamestoy.kitten.exception.LifecycleException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quijote {
  private final Logger logger = LoggerFactory.getLogger(Quijote.class);

  public void run() {
    var configuration =
        new ServerConfiguration(9290, 200, 200, Duration.of(30, ChronoUnit.SECONDS));
    try {
      new KittenServer(configuration).start();
    } catch (LifecycleException e) {
      logger.error("Error starting server", e);
    }
  }

  public static void main(String[] args) {
    new Quijote().run();
  }
}