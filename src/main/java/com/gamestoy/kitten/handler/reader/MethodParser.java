package com.gamestoy.kitten.handler.reader;

import com.gamestoy.kitten.handler.exception.InvalidMethodException;
import com.gamestoy.kitten.handler.exception.InvalidRequestException;
import com.gamestoy.kitten.http.HttpMethod;

class MethodParser {

  HttpMethod parse(String line) throws InvalidMethodException, InvalidRequestException {
    var words = line.split(" ");
    if (words.length > 0) {
      try {
        return HttpMethod.valueOf(words[0]);
      } catch (IllegalArgumentException e) {
        throw new InvalidMethodException();
      }
    } else {
      throw new InvalidRequestException("Error parsing HTTP method");
    }
  }
}
