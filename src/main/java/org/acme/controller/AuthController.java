package org.acme.controller;

import jakarta.inject.Inject;
import org.acme.dto.RegisterRequest;
import org.acme.dto.UserResponse;
import org.acme.entity.User;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.service.AuthService;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    public Response register(@Valid RegisterRequest request) {
        UserResponse response = authService.register(request);

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }
}