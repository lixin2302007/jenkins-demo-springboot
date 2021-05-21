package com.elane.learning.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component("loginAuthenticationFailureHandler")
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
      HttpServletResponse response, AuthenticationException e)
      throws IOException, ServletException {
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write("fail");
  }
}
