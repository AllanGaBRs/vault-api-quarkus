package org.acme.exception.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ApiValidationErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        List<FieldError> errors
) {
    public record FieldError(
            String field,
            String message
    ) {
    }
}