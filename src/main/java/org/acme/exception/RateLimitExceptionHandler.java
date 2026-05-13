package org.acme.exception;

import java.time.LocalDateTime;

import io.smallrye.faulttolerance.api.RateLimitException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.dto.ApiErrorResponse;

@Provider
public class RateLimitExceptionHandler implements ExceptionMapper<RateLimitException> {

    @Override
    public Response toResponse(RateLimitException exception) {

        ApiErrorResponse error = new ApiErrorResponse(
                LocalDateTime.now(),
                429,
                "Too Many Requests",
                "Muitas requisições. Tente novamente mais tarde."
        );

        return Response.status(429)
                .entity(error)
                .build();
    }
}