package org.academy.mapper;

import org.academy.dto.request.SemesterRequest;
import org.academy.dto.response.SemesterResponse;
import org.academy.entity.Semester;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SemesterMapper {
    public Semester toEntity(SemesterRequest request) {
        Semester semester = new Semester();
        semester.number = request.getNumber();
        semester.year = request.getYear();
        semester.startDate = request.getStartDate();
        semester.endDate = request.getEndDate();
        semester.active = request.isActive();
        return semester;
    }

    public SemesterResponse toResponse(Semester semester) {
        SemesterResponse response = new SemesterResponse();
        response.setId(semester.id);
        response.setNumber(semester.number);
        response.setYear(semester.year);
        response.setStartDate(semester.startDate);
        response.setEndDate(semester.endDate);
        response.setActive(semester.active);
        return response;
    }
}