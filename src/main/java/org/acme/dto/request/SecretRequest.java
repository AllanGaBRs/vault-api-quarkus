package org.acme.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SecretRequest(

        @NotBlank
        String title,

        @NotBlank
        String secretContent

) {
}