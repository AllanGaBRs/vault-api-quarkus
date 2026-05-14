package org.acme.exception;

import jakarta.ws.rs.core.Response;

public class InvalidUUIDException extends BusinessException {

    public InvalidUUIDException() {
        super("ID inválido.", Response.Status.BAD_REQUEST);
    }
}