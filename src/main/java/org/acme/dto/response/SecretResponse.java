package org.acme.dto.response;

import java.util.UUID;

public record SecretResponse(

        UUID id,
        String title,
        String secretContent

) {
}
