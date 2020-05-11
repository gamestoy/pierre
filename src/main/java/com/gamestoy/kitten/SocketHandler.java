package com.gamestoy.kitten;

import java.io.*;
import java.net.Socket;

public class SocketHandler {
  private Socket socket;

  public SocketHandler(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    try (var in = socket.getInputStream()) {
      var inBuffer = new PushbackInputStream(in);
      try {
        var firstLine = readLine(inBuffer);
        System.out.println(firstLine);
      } catch (Exception e) {
        System.out.println("Unexpected error: " + e.getMessage());
      }
    } catch (IOException e) {
      System.out.println("Error creating streams: " + e.getMessage());
    }
  }

  private String readLine(InputStream inputStream) throws IOException {
    StringBuilder sb = new StringBuilder();
    int c;
    while (((c = inputStream.read()) >= 0) && (c != 0x0a /* <LF> */)) {
      if (c != 0x0d /* <CR> */) {
        sb.append((char) c);
      } else {
        // Ignore <CR>.
      }
    }

    return sb.toString();
  }
}
