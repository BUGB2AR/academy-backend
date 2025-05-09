package org.academy.mapper;

import jakarta.inject.Named;
import org.academy.dto.request.UserRequest;
import org.academy.dto.response.UserResponse;
import org.academy.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Named("baseUserMapper")
public class UserMapper {
    public User toEntity(UserRequest request) {
        User user = new User();
        user.username = request.getUsername();
        user.email = request.getEmail();
        user.firstName = request.getFirstName();
        user.lastName = request.getLastName();
        user.password = request.getPassword();
        user.role = request.getRole();
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.id);
        response.setUsername(user.username);
        response.setEmail(user.email);
        return response;
    }
}
