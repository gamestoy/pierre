package com.gamestoy.kitten.filter;

import com.gamestoy.jdk.Filter;
import com.gamestoy.jdk.FilterChain;
import com.gamestoy.jdk.Servlet;
import com.gamestoy.jdk.ServletRequest;
import com.gamestoy.jdk.ServletResponse;
import java.util.List;

public class ServletFilterChain implements FilterChain {
  private final Servlet servlet;
  private final List<Filter> filters;
  private int filterIdx;

  public ServletFilterChain(Servlet servlet, List<Filter> filters) {
    this.servlet = servlet;
    this.filters = filters;
    this.filterIdx = 0;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response) {
    if (this.filterIdx < this.filters.size()) {
      var filter = this.filters.get(this.filterIdx++);
      filter.doFilter(request, response, this);
    } else {
      servlet.dispatch(request, response);
    }
  }
}
