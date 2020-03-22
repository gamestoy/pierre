package com.gamestoy.kitten.handler.writer;

import com.gamestoy.kitten.handler.exception.WriterException;
import com.gamestoy.kitten.http.HttpResponse;
import java.net.Socket;

public interface ResponseWriter {
  void write(Socket socket, HttpResponse httpResponse) throws WriterException;
}
