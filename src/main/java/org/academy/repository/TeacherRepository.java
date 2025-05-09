package org.academy.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.academy.entity.Teacher;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {
}