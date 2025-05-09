package org.academy.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.academy.dto.request.DisciplineRequest;
import org.academy.dto.response.DisciplineResponse;
import org.academy.entity.Discipline;
import org.academy.mapper.DisciplineMapper;
import org.academy.repository.DisciplineRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DisciplineService {
    @Inject
    DisciplineRepository disciplineRepository;

    @Inject
    DisciplineMapper disciplineMapper;

    public List<DisciplineResponse> findAll() {
        return disciplineRepository.listAll().stream()
                .map(disciplineMapper::toResponse)
                .collect(Collectors.toList());
    }

    public DisciplineResponse findById(Long id) {
        Discipline discipline = disciplineRepository.findById(id);
        if (discipline == null) {
            throw new NotFoundException("Discipline not found");
        }
        return disciplineMapper.toResponse(discipline);
    }

    public List<DisciplineResponse> findByCourseId(Long courseId) {
        return disciplineRepository.findByCourseId(courseId).stream()
                .map(disciplineMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public DisciplineResponse create(DisciplineRequest request) {
        Discipline discipline = disciplineMapper.toEntity(request);
        disciplineRepository.persist(discipline);
        return disciplineMapper.toResponse(discipline);
    }

    @Transactional
    public DisciplineResponse update(Long id, DisciplineRequest request) {
        Discipline discipline = disciplineRepository.findById(id);
        if (discipline == null) {
            throw new NotFoundException("Discipline not found");
        }

        discipline.name = request.getName();
        discipline.code = request.getCode();
        discipline.workload = request.getWorkload();
        discipline.semester = request.getSemester();
        // course and teacher are updated through their IDs in the mapper

        return disciplineMapper.toResponse(discipline);
    }

    @Transactional
    public void delete(Long id) {
        if (!disciplineRepository.deleteById(id)) {
            throw new NotFoundException("Discipline not found");
        }
    }
}