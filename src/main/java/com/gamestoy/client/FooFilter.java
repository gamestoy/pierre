package com.gamestoy.client;

import com.gamestoy.jdk.Filter;
import com.gamestoy.jdk.FilterChain;
import com.gamestoy.jdk.ServletRequest;
import com.gamestoy.jdk.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FooFilter implements Filter {
  private final Logger logger = LoggerFactory.getLogger(FooFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    logger.debug("FooFilter.pre doFilter");
    chain.doFilter(request, response);
    logger.debug("FooFilter.post doFilter");
  }
}
