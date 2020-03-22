package com.gamestoy.kitten.context;

import com.gamestoy.jdk.Servlet;
import com.gamestoy.jdk.Filter;
import java.util.ArrayList;
import java.util.List;

public class ServerContext {
  private final Servlet servlet;
  private final List<Filter> filters;

  public ServerContext(Servlet servlet, List<Filter> filters) {
    this.servlet = servlet;
    this.filters = new ArrayList<>(filters);
  }

  public ServerContext(Servlet servlet) {
    this.servlet = servlet;
    this.filters = new ArrayList<>();
  }

  public ServerContext addFilter(Filter filter) {
    filters.add(filter);
    return this;
  }

  public Servlet getServlet() {
    return servlet;
  }

  public List<Filter> getFilters() {
    return filters;
  }
}
