package org.academy.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.academy.dto.request.CurriculumRequest;
import org.academy.dto.response.CurriculumResponse;
import org.academy.entity.Curriculum;
import org.academy.entity.Discipline;
import org.academy.mapper.CurriculumMapper;
import org.academy.repository.CurriculumRepository;
import org.academy.repository.DisciplineRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CurriculumService {
    @Inject
    CurriculumRepository curriculumRepository;

    @Inject
    DisciplineRepository disciplineRepository;

    @Inject
    CurriculumMapper curriculumMapper;

    public List<CurriculumResponse> findAll() {
        return curriculumRepository.listAll().stream()
                .map(curriculumMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CurriculumResponse findById(Long id) {
        Curriculum curriculum = curriculumRepository.findById(id);
        if (curriculum == null) {
            throw new NotFoundException("Curriculum not found");
        }
        return curriculumMapper.toResponse(curriculum);
    }

    public List<CurriculumResponse> findByCourseId(Long courseId) {
        return curriculumRepository.findByCourseId(courseId).stream()
                .map(curriculumMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CurriculumResponse create(CurriculumRequest request) {
        Curriculum curriculum = curriculumMapper.toEntity(request);
        curriculumRepository.persist(curriculum);
        return curriculumMapper.toResponse(curriculum);
    }

    @Transactional
    public CurriculumResponse update(Long id, CurriculumRequest request) {
        Curriculum curriculum = curriculumRepository.findById(id);
        if (curriculum == null) {
            throw new NotFoundException("Curriculum not found");
        }

        curriculum.name = request.getName();
        curriculum.year = request.getYear();
        curriculum.active = request.isActive();
        return curriculumMapper.toResponse(curriculum);
    }

    @Transactional
    public CurriculumResponse addDiscipline(Long curriculumId, Long disciplineId) {
        Curriculum curriculum = curriculumRepository.findById(curriculumId);
        if (curriculum == null) {
            throw new NotFoundException("Curriculum not found");
        }

        Discipline discipline = disciplineRepository.findById(disciplineId);
        if (discipline == null) {
            throw new NotFoundException("Discipline not found");
        }

        curriculum.disciplines.add(discipline);
        return curriculumMapper.toResponse(curriculum);
    }

    @Transactional
    public void delete(Long id) {
        if (!curriculumRepository.deleteById(id)) {
            throw new NotFoundException("Curriculum not found");
        }
    }
}