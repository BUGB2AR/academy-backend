package org.academy.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.academy.dto.request.TeacherRequest;
import org.academy.dto.response.TeacherResponse;
import org.academy.entity.Teacher;
import org.academy.mapper.TeacherMapper;
import org.academy.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TeacherService {
    @Inject
    TeacherRepository teacherRepository;

    @Inject
    TeacherMapper teacherMapper;

    public List<TeacherResponse> findAll() {
        return teacherRepository.listAll().stream()
                .map(teacherMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TeacherResponse findById(Long id) {
        Teacher teacher = teacherRepository.findById(id);
        if (teacher == null) {
            throw new NotFoundException("Teacher not found");
        }
        return teacherMapper.toResponse(teacher);
    }

    @Transactional
    public TeacherResponse create(TeacherRequest request) {
        Teacher teacher = teacherMapper.toEntity(request);
        teacherRepository.persist(teacher);
        return teacherMapper.toResponse(teacher);
    }

    @Transactional
    public TeacherResponse update(Long id, TeacherRequest request) {
        Teacher teacher = teacherRepository.findById(id);
        if (teacher == null) {
            throw new NotFoundException("Teacher not found");
        }

        teacher.username = request.getUsername();
        teacher.email = request.getEmail();
        teacher.firstName = request.getFirstName();
        teacher.lastName = request.getLastName();
        teacher.role = request.getRole();
        teacher.department = request.getDepartment();
        teacher.qualification = request.getQualification();

        return teacherMapper.toResponse(teacher);
    }

    @Transactional
    public void delete(Long id) {
        if (!teacherRepository.deleteById(id)) {
            throw new NotFoundException("Teacher not found");
        }
    }
}