package com.gamestoy.jdk;

public interface Filter {
  void doFilter(ServletRequest request, ServletResponse response, FilterChain chain);
}
