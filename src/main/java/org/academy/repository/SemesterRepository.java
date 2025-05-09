package org.academy.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.academy.entity.Semester;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SemesterRepository implements PanacheRepository<Semester> {
    public List<Semester> findActiveSemesters() {
        return list("active", true);
    }
}