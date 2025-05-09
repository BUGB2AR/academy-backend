package org.academy.controller.common;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academy.dto.response.CurriculumResponse;
import org.academy.service.CurriculumService;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/common/curricula")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin", "coordinator", "teacher", "student"})
public class CommonCurriculumController {
    @Inject
    CurriculumService curriculumService;

    @GET
    @Path("/course/{courseId}")
    @Operation(summary = "Get curricula by course")
    public Response getByCourse(@PathParam("courseId") Long courseId) {
        return Response.ok(curriculumService.findByCourseId(courseId)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get curriculum by ID")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(curriculumService.findById(id)).build();
    }

    @GET
    @Path("/{id}/disciplines")
    @Operation(summary = "Get disciplines by curriculum")
    public Response getDisciplines(@PathParam("id") Long id) {
        CurriculumResponse curriculum = curriculumService.findById(id);
        return Response.ok(curriculum.getDisciplineIds()).build();
    }
}
