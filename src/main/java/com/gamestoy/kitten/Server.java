package com.gamestoy.kitten;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
  private int port;

  public Server(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    var server = new ServerSocket(this.port);
    while (true) {
      try {
        var socket = server.accept();
        System.out.println("Se ha conectado un nuevo cliente");
        var worker = new SocketHandler(socket);
        worker.run();
      } catch (IOException e) {
        System.out.println("Error listening to connection: " + e.getMessage());
      }
    }
  }
}
