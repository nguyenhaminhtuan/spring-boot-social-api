package io.github.nguyenhaminhtuan.springbootsocialapi.service;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.UserResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.mapper.UserMapper;
import io.github.nguyenhaminhtuan.springbootsocialapi.model.User;
import io.github.nguyenhaminhtuan.springbootsocialapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public Optional<UserResponse> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(mapper.toUserResponse(user.get()));
    }
}
