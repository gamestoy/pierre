package com.gamestoy.kitten.configuration;

import java.time.Duration;

public class ServerConfiguration {
  private final int port;
  private final int connections;
  private final int workers;
  private final Duration keepAlive;

  public ServerConfiguration(int port, int connections, int workers, Duration keepAlive) {
    this.port = port;
    this.connections = connections;
    this.workers = workers;
    this.keepAlive = keepAlive;
  }

  public int getPort() {
    return port;
  }

  public int getConnections() {
    return connections;
  }

  public int getWorkers() {
    return workers;
  }

  public Duration getKeepAlive() {
    return keepAlive;
  }
}
