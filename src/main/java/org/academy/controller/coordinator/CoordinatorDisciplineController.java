package org.academy.controller.coordinator;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academy.dto.request.DisciplineRequest;
import org.academy.service.DisciplineService;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/coordinator/disciplines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin", "coordinator"})
public class CoordinatorDisciplineController {
    @Inject
    DisciplineService disciplineService;

    @GET
    @Operation(summary = "List all disciplines")
    public Response listAll() {
        return Response.ok(disciplineService.findAll()).build();
    }

    @GET
    @Path("/course/{courseId}")
    @Operation(summary = "List disciplines by course")
    public Response listByCourse(@PathParam("courseId") Long courseId) {
        return Response.ok(disciplineService.findByCourseId(courseId)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get discipline by ID")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(disciplineService.findById(id)).build();
    }

    @POST
    @Operation(summary = "Create a new discipline")
    public Response create(DisciplineRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(disciplineService.create(request))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a discipline")
    public Response update(@PathParam("id") Long id, DisciplineRequest request) {
        return Response.ok(disciplineService.update(id, request)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a discipline")
    public Response delete(@PathParam("id") Long id) {
        disciplineService.delete(id);
        return Response.noContent().build();
    }
}
