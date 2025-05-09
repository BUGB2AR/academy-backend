package org.academy.controller.user;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academy.dto.request.UserRequest;
import org.academy.dto.response.UserResponse;
import org.academy.service.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Path("/register")
    public Response register(UserRequest userRequest) {
        UserResponse response = userService.register(userRequest);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @POST
    @Path("/login")
    public Response login(@QueryParam("username") String username,
                          @QueryParam("password") String password) {
        UserResponse response = userService.authenticate(username, password);
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        UserResponse response = userService.findById(id);
        return Response.ok(response).build();
    }
}