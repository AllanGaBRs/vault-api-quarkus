package org.acme.exception.handler;

import java.time.LocalDateTime;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.dto.ApiErrorResponse;

@Provider
public class NotAuthorizedExceptionHandler
        implements ExceptionMapper<NotAuthorizedException> {

    @Override
    public Response toResponse(NotAuthorizedException exception) {

        ApiErrorResponse error =
                new ApiErrorResponse(
                        LocalDateTime.now(),
                        401,
                        "Unauthorized",
                        "Token ausente ou inválido."
                );

        return Response.status(
                        Response.Status.UNAUTHORIZED
                )
                .entity(error)
                .build();
    }
}