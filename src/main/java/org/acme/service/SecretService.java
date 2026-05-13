package org.acme.service;

import java.util.UUID;

import org.acme.dto.request.SecretRequest;
import org.acme.dto.response.SecretResponse;
import org.acme.entity.Secret;
import org.acme.entity.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class SecretService {

    @Transactional
    public SecretResponse create(
            UUID userId,
            SecretRequest request
    ) {

        User user = User.findById(userId);

        if (user == null) {
            throw new WebApplicationException(
                    "Usuário não encontrado.",
                    Response.Status.NOT_FOUND
            );
        }

        Secret secret = new Secret();
        secret.title = request.title();
        secret.secretContent = request.secretContent();
        secret.user = user;

        secret.persist();

        return new SecretResponse(
                secret.id,
                secret.title,
                secret.secretContent
        );
    }
}