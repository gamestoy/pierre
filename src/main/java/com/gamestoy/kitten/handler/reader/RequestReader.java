package com.gamestoy.kitten.handler.reader;

import com.gamestoy.kitten.handler.exception.InvalidRequestException;
import com.gamestoy.kitten.handler.exception.ReaderException;
import com.gamestoy.kitten.http.HttpRequest;
import java.net.Socket;

public interface RequestReader {
  HttpRequest read(Socket socket) throws InvalidRequestException, ReaderException;
}
