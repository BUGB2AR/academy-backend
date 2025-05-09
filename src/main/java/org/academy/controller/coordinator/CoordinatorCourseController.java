package org.academy.controller.coordinator;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academy.dto.request.CourseRequest;
import org.academy.service.CourseService;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/coordinator/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin", "coordinator"})
public class CoordinatorCourseController {
    @Inject
    CourseService courseService;

    @GET
    @Operation(summary = "List all courses")
    public Response listAll() {
        return Response.ok(courseService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get course by ID")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(courseService.findById(id)).build();
    }

    @POST
    @Operation(summary = "Create a new course")
    public Response create(CourseRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(courseService.create(request))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a course")
    public Response update(@PathParam("id") Long id, CourseRequest request) {
        return Response.ok(courseService.update(id, request)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a course")
    public Response delete(@PathParam("id") Long id) {
        courseService.delete(id);
        return Response.noContent().build();
    }
}
