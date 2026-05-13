package org.acme.exception.auth;

import jakarta.ws.rs.core.Response;
import org.acme.exception.BusinessException;

public class InvalidCredentialsException extends BusinessException {

    public InvalidCredentialsException() {
        super("Email ou senha inválidos.", Response.Status.UNAUTHORIZED);
    }
}