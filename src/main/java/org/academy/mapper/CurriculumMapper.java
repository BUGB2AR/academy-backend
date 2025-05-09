package org.academy.mapper;

import org.academy.dto.request.CurriculumRequest;
import org.academy.dto.response.CurriculumResponse;
import org.academy.entity.Curriculum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.academy.repository.CourseRepository;
import org.academy.repository.DisciplineRepository;

import java.util.stream.Collectors;

@ApplicationScoped
public class CurriculumMapper {
    @Inject
    CourseRepository courseRepository;

    @Inject
    DisciplineRepository disciplineRepository;

    public Curriculum toEntity(CurriculumRequest request) {
        Curriculum curriculum = new Curriculum();
        curriculum.name = request.getName();
        curriculum.year = request.getYear();
        curriculum.active = request.isActive();
        curriculum.course = courseRepository.findById(request.getCourseId());

        if (request.getDisciplineIds() != null) {
            curriculum.disciplines = request.getDisciplineIds().stream()
                    .map(disciplineRepository::findById)
                    .collect(Collectors.toSet());
        }

        return curriculum;
    }

    public CurriculumResponse toResponse(Curriculum curriculum) {
        CurriculumResponse response = new CurriculumResponse();
        response.setId(curriculum.id);
        response.setName(curriculum.name);
        response.setYear(curriculum.year);
        response.setActive(curriculum.active);
        response.setCourseId(curriculum.course != null ? curriculum.course.id : null);

        if (curriculum.disciplines != null) {
            response.setDisciplineIds(curriculum.disciplines.stream()
                    .map(d -> d.id)
                    .collect(Collectors.toSet()));
        }

        return response;
    }
}