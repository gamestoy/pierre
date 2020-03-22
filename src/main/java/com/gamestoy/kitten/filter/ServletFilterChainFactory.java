package com.gamestoy.kitten.filter;

import com.gamestoy.jdk.Filter;
import com.gamestoy.kitten.configuration.ServerConfiguration;
import com.gamestoy.kitten.context.ServerContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServletFilterChainFactory {
  private final List<Filter> defaultFilters;

  public ServletFilterChainFactory(ServerConfiguration configuration) {
    this.defaultFilters = Arrays.asList(new RemoteAddressFilter(configuration.getBannedIps()));
  }

  public ServletFilterChain createDefault(ServerContext context) {
    var filters =
        Stream.concat(defaultFilters.stream(), context.getFilters().stream())
            .collect(Collectors.toList());
    return new ServletFilterChain(context.getServlet(), filters);
  }
}
