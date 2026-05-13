package org.acme.controller;

import org.acme.dto.RegisterRequest;
import org.acme.entity.User;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @POST
    @Path("/register")
    @Transactional
    public Response register(@Valid RegisterRequest request) {
        User existingUser = User.find("email", request.email()).firstResult();

        if (existingUser != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Email já cadastrado.")
                    .build();
        }

        User user = new User();
        user.name = request.name();
        user.email = request.email();
        user.password = request.password();

        user.persist();

        return Response.status(Response.Status.CREATED)
                .entity("Usuário registrado com sucesso.")
                .build();
    }
}