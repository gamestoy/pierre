package com.gamestoy.kitten.filter;

import com.gamestoy.jdk.Filter;
import com.gamestoy.jdk.FilterChain;
import com.gamestoy.jdk.ServletRequest;
import com.gamestoy.jdk.ServletResponse;
import com.gamestoy.kitten.http.HttpHeaders;
import com.gamestoy.kitten.http.HttpStatus;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteAddressFilter implements Filter {
  private final Logger logger = LoggerFactory.getLogger(RemoteAddressFilter.class);
  private final Set<String> bannedIps;

  public RemoteAddressFilter(Set<String> bannedIps) {
    this.bannedIps = bannedIps;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    if (!bannedIps.isEmpty()
        && (bannedIps.contains(request.getRemoteAddress()) || checkHeaders(request))) {
      logger.info("Rejecting request: banned IP");
      response.setStatusCode(HttpStatus.FORBIDDEN.getCode());
    } else {
      chain.doFilter(request, response);
    }
  }

  private boolean checkHeaders(ServletRequest request) {
    return Optional.ofNullable(request.getHeaders().get(HttpHeaders.X_FORWARDED_FOR))
        .map(values -> values.stream().anyMatch(bannedIps::contains))
        .orElse(false);
  }
}
