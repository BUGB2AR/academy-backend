package org.academy.mapper;

import org.academy.dto.request.CourseRequest;
import org.academy.dto.response.CourseResponse;
import org.academy.entity.Course;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CourseMapper {
    public Course toEntity(CourseRequest request) {
        Course course = new Course();
        course.name = request.getName();
        course.code = request.getCode();
        course.durationSemesters = request.getDurationSemesters();
        return course;
    }

    public CourseResponse toResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.id);
        response.setName(course.name);
        response.setCode(course.code);
        response.setDurationSemesters(course.durationSemesters);
        return response;
    }
}