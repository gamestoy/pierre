package com.gamestoy.client;

import com.gamestoy.jdk.Servlet;
import com.gamestoy.jdk.ServletRequest;
import com.gamestoy.jdk.ServletResponse;
import com.gamestoy.kitten.http.HttpHeaders;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationServlet implements Servlet {
  private final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);

  @Override
  public void dispatch(ServletRequest request, ServletResponse response) {
    logger.debug("ApplicationServlet.doDispatch");
    var body = "test";
    response.setBody(Optional.of(body));
    response.addHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
    response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(body.length()));
  }
}
