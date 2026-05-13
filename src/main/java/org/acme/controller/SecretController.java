package org.acme.controller;

import java.util.UUID;

import jakarta.ws.rs.*;
import org.acme.dto.request.SecretRequest;
import org.acme.dto.response.SecretResponse;
import org.acme.service.SecretService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
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

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID secretId) {

        UUID userId = UUID.fromString(
                jwt.getClaim("userId")
        );

        SecretResponse response = secretService.findById(
                userId,
                secretId
        );

        return Response.ok(response).build();
    }
}