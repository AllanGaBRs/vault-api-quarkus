package org.acme.exception;

import jakarta.ws.rs.core.Response;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException() {
        super("Email já cadastrado.", Response.Status.CONFLICT);
    }
}