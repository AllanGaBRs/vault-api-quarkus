package org.acme.exception;

import jakarta.ws.rs.core.Response;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException() {
        super("Email ou senha inválidos.", Response.Status.UNAUTHORIZED);
    }
}