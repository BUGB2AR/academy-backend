package org.academy.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.academy.dto.request.UserRequest;
import org.academy.dto.response.UserResponse;
import org.academy.entity.User;
import org.academy.mapper.UserMapper;
import org.academy.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Inject
    @Named("baseUserMapper")
    UserMapper userMapper;

    public List<UserResponse> findAll() {
        return userRepository.listAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        User user = userMapper.toEntity(request);
        userRepository.persist(user);
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse update(Long id, UserRequest request) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        user.username = request.getUsername();
        user.email = request.getEmail();
        user.firstName = request.getFirstName();
        user.lastName = request.getLastName();
        user.role = request.getRole();

        return userMapper.toResponse(user);
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.deleteById(id)) {
            throw new NotFoundException("User not found");
        }
    }

    @Transactional
    public UserResponse register(UserRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new WebApplicationException("Username already exists", Response.Status.CONFLICT);
        }

        User user = userMapper.toEntity(request);
        userRepository.persist(user);
        return userMapper.toResponse(user);
    }

    public UserResponse authenticate(String username, String password) {
        User user = userRepository.find("username", username).firstResult();

        if (user == null) {
            throw new WebApplicationException("User not found", Response.Status.NOT_FOUND);
        }

        if (!user.getPassword().equals(password)) {
            throw new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);
        }

        return userMapper.toResponse(user);
    }

    public boolean userExists(String username) {
        return userRepository.count("username", username) > 0;
    }
}