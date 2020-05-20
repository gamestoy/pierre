package com.gamestoy.kitten.handler.reader;

import java.io.IOException;
import java.io.InputStream;

class LineParser {

  public String readLine(InputStream inputStream) throws IOException {
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
