package org.academy.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.academy.entity.Discipline;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DisciplineRepository implements PanacheRepository<Discipline> {
    public List<Discipline> findByCourseId(Long courseId) {
        return list("course.id", courseId);
    }
}