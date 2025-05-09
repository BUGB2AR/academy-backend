package org.academy.controller.coordinator;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academy.dto.request.CurriculumRequest;
import org.academy.service.CurriculumService;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/coordinator/curricula")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin", "coordinator"})
public class CoordinatorCurriculumController {
    @Inject
    CurriculumService curriculumService;

    @GET
    @Operation(summary = "List all curricula")
    public Response listAll() {
        return Response.ok(curriculumService.findAll()).build();
    }

    @GET
    @Path("/course/{courseId}")
    @Operation(summary = "List curricula by course")
    public Response listByCourse(@PathParam("courseId") Long courseId) {
        return Response.ok(curriculumService.findByCourseId(courseId)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get curriculum by ID")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(curriculumService.findById(id)).build();
    }

    @POST
    @Operation(summary = "Create a new curriculum")
    public Response create(CurriculumRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(curriculumService.create(request))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a curriculum")
    public Response update(@PathParam("id") Long id, CurriculumRequest request) {
        return Response.ok(curriculumService.update(id, request)).build();
    }

    @POST
    @Path("/{curriculumId}/disciplines/{disciplineId}")
    @Operation(summary = "Add discipline to curriculum")
    public Response addDiscipline(
            @PathParam("curriculumId") Long curriculumId,
            @PathParam("disciplineId") Long disciplineId) {
        return Response.ok(curriculumService.addDiscipline(curriculumId, disciplineId)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a curriculum")
    public Response delete(@PathParam("id") Long id) {
        curriculumService.delete(id);
        return Response.noContent().build();
    }
}
