package org.academy.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.academy.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public List<User> findByRole(String role) {
        return list("role", role);
    }
}