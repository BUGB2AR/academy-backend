package org.academy.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.academy.entity.Curriculum;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CurriculumRepository implements PanacheRepository<Curriculum> {
    public List<Curriculum> findByCourseId(Long courseId) {
        return list("course.id", courseId);
    }
}