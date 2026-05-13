package org.acme.service;

import java.util.UUID;

import jakarta.inject.Inject;
import org.acme.dto.request.SecretRequest;
import org.acme.dto.response.SecretResponse;
import org.acme.entity.Secret;
import org.acme.entity.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.exception.SecretNotFoundException;
import org.acme.exception.UserNotFoundException;
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
            throw new UserNotFoundException();
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

    public SecretResponse findById(UUID userId, UUID secretId) {

        Secret secret = Secret.find(
                "id = ?1 and user.id = ?2",
                secretId,
                userId
        ).firstResult();

        if (secret == null) {
            throw new SecretNotFoundException();
        }

        return new SecretResponse(
                secret.id,
                cryptoService.decrypt(secret.title),
                cryptoService.decrypt(secret.secretContent)
        );
    }
}