package org.academy.mapper;

import org.academy.dto.request.StudentRequest;
import org.academy.dto.response.StudentResponse;
import org.academy.entity.Student;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentMapper extends UserMapper {
    public Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.username = request.getUsername();
        student.email = request.getEmail();
        student.firstName = request.getFirstName();
        student.lastName = request.getLastName();
        student.password = request.getPassword();
        student.role = request.getRole();
        student.registrationNumber = request.getRegistrationNumber();
        student.enrollmentDate = request.getEnrollmentDate();
        return student;
    }

    public StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.id);
        response.setUsername(student.username);
        response.setEmail(student.email);
        response.setRegistrationNumber(student.registrationNumber);
        response.setEnrollmentDate(student.enrollmentDate);
        return response;
    }
}