package org.acme.exception;

import jakarta.ws.rs.core.Response;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("Usuário não encontrado.", Response.Status.NOT_FOUND);
    }
}