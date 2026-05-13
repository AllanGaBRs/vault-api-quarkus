package org.acme.exception.auth;

import jakarta.ws.rs.core.Response;
import org.acme.exception.BusinessException;

public class EmailAlreadyExistsException extends BusinessException {

    public EmailAlreadyExistsException() {
        super("Email já cadastrado.", Response.Status.CONFLICT);
    }
}