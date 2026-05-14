package org.acme.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SecretRequest(

        @NotBlank
        @Size(max = 200)
        String title,

        @NotBlank
        @Size(max = 10000)
        String secretContent

) {
}