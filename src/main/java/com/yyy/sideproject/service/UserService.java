package com.yyy.sideproject.service;

import com.yyy.sideproject.domain.User;
import com.yyy.sideproject.dto.UserRequest;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User saved = userRepository.save(user);

        return new UserResponse(saved.getId(), saved.getName(), saved.getEmail());
    }

    public UserResponse getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}