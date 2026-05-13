package org.acme.exception;

import jakarta.ws.rs.core.Response;

public class SecretNotFoundException extends BusinessException {
  public SecretNotFoundException() {
    super("Segredo não encontrado.", Response.Status.NOT_FOUND);
  }
}