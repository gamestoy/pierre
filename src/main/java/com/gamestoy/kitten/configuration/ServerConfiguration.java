package com.gamestoy.kitten.configuration;

public class ServerConfiguration {
  private final int port;
  private final int connections;

  public ServerConfiguration(int port, int connections) {
    this.port = port;
    this.connections = connections;
  }

  public int getPort() {
    return port;
  }

  public int getConnections() {
    return connections;
  }
}
