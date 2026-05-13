package org.acme.exception.handler;

import java.time.LocalDateTime;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.BusinessException;
import org.acme.exception.dto.ApiErrorResponse;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {

        ApiErrorResponse error = new ApiErrorResponse(
                LocalDateTime.now(),
                exception.getStatus().getStatusCode(),
                exception.getStatus().getReasonPhrase(),
                exception.getMessage()
        );

        return Response.status(exception.getStatus())
                .entity(error)
                .build();
    }
}