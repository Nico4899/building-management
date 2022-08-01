package edu.kit.tm.cm.smartcampus.buildingmanagement.api.configuration.error;

import org.springframework.security.core.AuthenticationException;

public class ServerAuthenticationException extends AuthenticationException {

  public ServerAuthenticationException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ServerAuthenticationException(String msg) {
    super(msg);
  }
}
