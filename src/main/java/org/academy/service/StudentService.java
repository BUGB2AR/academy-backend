package org.academy.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.academy.dto.request.StudentRequest;
import org.academy.dto.response.StudentResponse;
import org.academy.entity.Student;
import org.academy.mapper.StudentMapper;
import org.academy.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentService {
    @Inject
    StudentRepository studentRepository;

    @Inject
    StudentMapper studentMapper;

    public List<StudentResponse> findAll() {
        return studentRepository.listAll().stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse findById(Long id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new NotFoundException("Student not found");
        }
        return studentMapper.toResponse(student);
    }

    @Transactional
    public StudentResponse create(StudentRequest request) {
        Student student = studentMapper.toEntity(request);
        studentRepository.persist(student);
        return studentMapper.toResponse(student);
    }

    @Transactional
    public StudentResponse update(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        student.username = request.getUsername();
        student.email = request.getEmail();
        student.firstName = request.getFirstName();
        student.lastName = request.getLastName();
        student.role = request.getRole();
        student.registrationNumber = request.getRegistrationNumber();
        student.enrollmentDate = request.getEnrollmentDate();

        return studentMapper.toResponse(student);
    }

    @Transactional
    public void delete(Long id) {
        if (!studentRepository.deleteById(id)) {
            throw new NotFoundException("Student not found");
        }
    }
}