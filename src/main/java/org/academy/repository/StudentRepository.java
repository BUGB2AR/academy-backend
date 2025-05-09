package org.academy.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.academy.entity.Student;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
}