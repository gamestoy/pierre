package com.gamestoy.kitten.configuration;

import java.time.Duration;
import java.util.Set;

public class ServerConfiguration {
  private final int port;
  private final int connections;
  private final int workers;
  private final Duration keepAlive;
  private final Set<String> bannedIps;

  public ServerConfiguration(
      int port, int connections, int workers, Duration keepAlive, Set<String> bannedIps) {
    this.port = port;
    this.connections = connections;
    this.workers = workers;
    this.keepAlive = keepAlive;
    this.bannedIps = bannedIps;
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

  public Set<String> getBannedIps() {
    return bannedIps;
  }
}
