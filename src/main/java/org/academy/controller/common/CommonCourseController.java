package org.academy.controller.common;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academy.service.CourseService;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/common/courses")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin", "coordinator", "teacher", "student"})
public class CommonCourseController {
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
}
