package org.acme.exception.handler;

import java.time.LocalDateTime;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.dto.ApiErrorResponse;

@Provider
public class ForbiddenExceptionHandler
        implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(ForbiddenException exception) {

        ApiErrorResponse error =
                new ApiErrorResponse(
                        LocalDateTime.now(),
                        403,
                        "Forbidden",
                        "Acesso negado."
                );

        return Response.status(
                        Response.Status.FORBIDDEN
                )
                .entity(error)
                .build();
    }
}