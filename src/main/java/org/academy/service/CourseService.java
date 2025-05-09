package org.academy.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.academy.dto.request.CourseRequest;
import org.academy.dto.response.CourseResponse;
import org.academy.entity.Course;
import org.academy.mapper.CourseMapper;
import org.academy.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CourseService {
    @Inject
    CourseRepository courseRepository;

    @Inject
    CourseMapper courseMapper;

    public List<CourseResponse> findAll() {
        return courseRepository.listAll().stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse findById(Long id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        return courseMapper.toResponse(course);
    }

    @Transactional
    public CourseResponse create(CourseRequest request) {
        Course course = courseMapper.toEntity(request);
        courseRepository.persist(course);
        return courseMapper.toResponse(course);
    }

    @Transactional
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }

        course.name = request.getName();
        course.code = request.getCode();
        course.durationSemesters = request.getDurationSemesters();

        return courseMapper.toResponse(course);
    }

    @Transactional
    public void delete(Long id) {
        if (!courseRepository.deleteById(id)) {
            throw new NotFoundException("Course not found");
        }
    }
}