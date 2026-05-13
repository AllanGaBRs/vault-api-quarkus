package org.acme.controller;

import java.util.UUID;

import org.acme.dto.request.SecretRequest;
import org.acme.dto.response.SecretResponse;
import org.acme.service.SecretService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/secrets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SecretController {

    @Inject
    SecretService secretService;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response create(@Valid SecretRequest request) {

        UUID userId = UUID.fromString(
                jwt.getClaim("userId")
        );

        SecretResponse response = secretService.create(
                userId,
                request
        );

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }
}