package org.academy.mapper;

import org.academy.dto.request.DisciplineRequest;
import org.academy.dto.response.DisciplineResponse;
import org.academy.entity.Discipline;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.academy.repository.CourseRepository;
import org.academy.repository.TeacherRepository;

@ApplicationScoped
public class DisciplineMapper {
    @Inject
    CourseRepository courseRepository;

    @Inject
    TeacherRepository teacherRepository;

    public Discipline toEntity(DisciplineRequest request) {
        Discipline discipline = new Discipline();
        discipline.name = request.getName();
        discipline.code = request.getCode();
        discipline.workload = request.getWorkload();
        discipline.semester = request.getSemester();
        discipline.course = courseRepository.findById(request.getCourseId());
        discipline.teacher = teacherRepository.findById(request.getTeacherId());
        return discipline;
    }

    public DisciplineResponse toResponse(Discipline discipline) {
        DisciplineResponse response = new DisciplineResponse();
        response.setId(discipline.id);
        response.setName(discipline.name);
        response.setCode(discipline.code);
        response.setWorkload(discipline.workload);
        response.setSemester(discipline.semester);
        response.setCourseId(discipline.course != null ? discipline.course.id : null);
        response.setTeacherId(discipline.teacher != null ? discipline.teacher.id : null);
        return response;
    }
}