package org.acme.service;

import org.acme.dto.RegisterRequest;
import org.acme.dto.UserResponse;
import org.acme.entity.User;
import org.acme.security.PasswordService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AuthService {

    @Inject
    PasswordService passwordService;

    @Transactional
    public UserResponse register(RegisterRequest request) {
        User existingUser = User.find("email", request.email()).firstResult();

        if (existingUser != null) {
            throw new WebApplicationException(
                    "Email já cadastrado.",
                    Response.Status.CONFLICT
            );
        }

        User user = new User();
        user.name = request.name();
        user.email = request.email();
        user.password = passwordService.hash(request.password());

        user.persist();

        return new UserResponse(
                user.id,
                user.name,
                user.email
        );
    }
}