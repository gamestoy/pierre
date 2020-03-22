package com.gamestoy.jdk;

public interface FilterChain {
  void doFilter(ServletRequest request, ServletResponse response);
}
