package org.acme.exception.handler;

import java.time.LocalDateTime;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.dto.ApiErrorResponse;
import org.jboss.logging.Logger;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GenericExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        LOG.error("Erro interno ao processar requisição.");

        ApiErrorResponse error = new ApiErrorResponse(
                LocalDateTime.now(),
                500,
                "Internal Server Error",
                "Erro interno."
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}