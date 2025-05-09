package org.academy.mapper;

import org.academy.dto.request.TeacherRequest;
import org.academy.dto.response.TeacherResponse;
import org.academy.entity.Teacher;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeacherMapper extends UserMapper {
    public Teacher toEntity(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.username = request.getUsername();
        teacher.email = request.getEmail();
        teacher.firstName = request.getFirstName();
        teacher.lastName = request.getLastName();
        teacher.password = request.getPassword();
        teacher.role = request.getRole();
        teacher.department = request.getDepartment();
        teacher.qualification = request.getQualification();
        return teacher;
    }

    public TeacherResponse toResponse(Teacher teacher) {
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.id);
        response.setUsername(teacher.username);
        response.setEmail(teacher.email);
        response.setDepartment(teacher.department);
        response.setQualification(teacher.qualification);
        return response;
    }
}