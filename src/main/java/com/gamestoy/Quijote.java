package com.gamestoy;

import com.gamestoy.kitten.KittenServer;
import com.gamestoy.kitten.configuration.ServerConfiguration;
import com.gamestoy.kitten.exception.LifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quijote {
  private final Logger logger = LoggerFactory.getLogger(Quijote.class);

  public void run() {
    var configuration = new ServerConfiguration(9290, 100);
    var server = new KittenServer(configuration);
    try {
      server.start();
    } catch (LifecycleException e) {
      logger.error("Error creating server", e);
    }
  }

  public static void main(String[] args) {
    new Quijote().run();
  }
}
