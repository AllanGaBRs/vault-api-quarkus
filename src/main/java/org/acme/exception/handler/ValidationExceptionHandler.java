package org.acme.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.dto.ApiValidationErrorResponse;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<ApiValidationErrorResponse.FieldError> errors = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ApiValidationErrorResponse.FieldError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()
                ))
                .toList();

        ApiValidationErrorResponse response = new ApiValidationErrorResponse(
                LocalDateTime.now(),
                400,
                "Bad Request",
                "Dados inválidos.",
                errors
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}