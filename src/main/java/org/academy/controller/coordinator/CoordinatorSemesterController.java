package org.academy.controller.coordinator;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academy.dto.request.SemesterRequest;
import org.academy.service.SemesterService;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/coordinator/semesters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin", "coordinator"})
public class CoordinatorSemesterController {
    @Inject
    SemesterService semesterService;

    @GET
    @Operation(summary = "List all semesters")
    public Response listAll() {
        return Response.ok(semesterService.findAll()).build();
    }

    @GET
    @Path("/active")
    @Operation(summary = "List active semesters")
    public Response listActive() {
        return Response.ok(semesterService.findActiveSemesters()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get semester by ID")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(semesterService.findById(id)).build();
    }

    @POST
    @Operation(summary = "Create a new semester")
    public Response create(SemesterRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(semesterService.create(request))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a semester")
    public Response update(@PathParam("id") Long id, SemesterRequest request) {
        return Response.ok(semesterService.update(id, request)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a semester")
    public Response delete(@PathParam("id") Long id) {
        semesterService.delete(id);
        return Response.noContent().build();
    }
}