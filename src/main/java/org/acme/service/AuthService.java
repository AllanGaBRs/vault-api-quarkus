package org.acme.service;

import org.acme.dto.request.LoginRequest;
import org.acme.dto.request.RegisterRequest;
import org.acme.dto.response.LoginResponse;
import org.acme.dto.response.UserResponse;
import org.acme.entity.User;
import org.acme.security.PasswordService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.time.Duration;
import io.smallrye.jwt.build.Jwt;

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

    public LoginResponse login(LoginRequest request) {
        User user = User.find("email", request.email()).firstResult();

        if (user == null) {
            throw new WebApplicationException(
                    "Email ou senha inválidos.",
                    Response.Status.UNAUTHORIZED
            );
        }

        boolean passwordMatches = passwordService.matches(
                request.password(),
                user.password
        );

        if (!passwordMatches) {
            throw new WebApplicationException(
                    "Email ou senha inválidos.",
                    Response.Status.UNAUTHORIZED
            );
        }

        String token = Jwt.issuer("cofre-digital")
                .subject(user.email)
                .claim("userId", user.id.toString())
                .expiresIn(Duration.ofHours(1))
                .sign();

        return new LoginResponse(token);
    }
}