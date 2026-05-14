package org.acme.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank
        @Size(max = 100)
        String name,

        @Email
        @NotBlank
        @Size(max = 100)
        String email,

        @NotBlank
        @Size(min = 12, max = 100)
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{12,}$",
                message = "A senha deve conter no mínimo 12 caracteres, letra maiúscula, letra minúscula, número e caractere especial."
        )
        String password

) {
}