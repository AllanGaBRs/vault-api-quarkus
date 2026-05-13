package org.acme.service;

import java.util.UUID;

import jakarta.inject.Inject;
import org.acme.dto.request.SecretRequest;
import org.acme.dto.response.SecretResponse;
import org.acme.entity.Secret;
import org.acme.entity.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.security.CryptoService;

@ApplicationScoped
public class SecretService {

    @Inject
    CryptoService cryptoService;

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
        secret.title = cryptoService.encrypt(request.title());
        secret.secretContent = cryptoService.encrypt(request.secretContent());
        secret.user = user;

        secret.persist();

        return new SecretResponse(
                secret.id,
                request.title(),
                request.secretContent()
        );
    }
}