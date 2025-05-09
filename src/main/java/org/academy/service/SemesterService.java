package org.academy.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.academy.dto.request.SemesterRequest;
import org.academy.dto.response.SemesterResponse;
import org.academy.entity.Semester;
import org.academy.mapper.SemesterMapper;
import org.academy.repository.SemesterRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SemesterService {
    @Inject
    SemesterRepository semesterRepository;

    @Inject
    SemesterMapper semesterMapper;

    public List<SemesterResponse> findAll() {
        return semesterRepository.listAll().stream()
                .map(semesterMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<SemesterResponse> findActiveSemesters() {
        return semesterRepository.findActiveSemesters().stream()
                .map(semesterMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SemesterResponse findById(Long id) {
        Semester semester = semesterRepository.findById(id);
        if (semester == null) {
            throw new NotFoundException("Semester not found");
        }
        return semesterMapper.toResponse(semester);
    }

    @Transactional
    public SemesterResponse create(SemesterRequest request) {
        Semester semester = semesterMapper.toEntity(request);
        semesterRepository.persist(semester);
        return semesterMapper.toResponse(semester);
    }

    @Transactional
    public SemesterResponse update(Long id, SemesterRequest request) {
        Semester semester = semesterRepository.findById(id);
        if (semester == null) {
            throw new NotFoundException("Semester not found");
        }

        semester.number = request.getNumber();
        semester.year = request.getYear();
        semester.startDate = request.getStartDate();
        semester.endDate = request.getEndDate();
        semester.active = request.isActive();

        return semesterMapper.toResponse(semester);
    }

    @Transactional
    public void delete(Long id) {
        if (!semesterRepository.deleteById(id)) {
            throw new NotFoundException("Semester not found");
        }
    }
}