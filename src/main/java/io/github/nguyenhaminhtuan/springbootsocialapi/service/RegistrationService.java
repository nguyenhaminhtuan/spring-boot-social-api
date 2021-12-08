package io.github.nguyenhaminhtuan.springbootsocialapi.service;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.RegisterUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.UserResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.exception.UsernameAlreadyExistsException;
import io.github.nguyenhaminhtuan.springbootsocialapi.mapper.UserMapper;
import io.github.nguyenhaminhtuan.springbootsocialapi.model.User;
import io.github.nguyenhaminhtuan.springbootsocialapi.model.UserRole;
import io.github.nguyenhaminhtuan.springbootsocialapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserResponse registerUser(RegisterUserRequest request) throws UsernameAlreadyExistsException {
        log.debug("Registering user {}", request);
        String username = request.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException(String.format("Username %s already exists", username));
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(UserRole.USER);
        userRepository.save(user);
        log.info("Username {} with role {} registered", user.getUsername(), user.getRole());

        return mapper.toUserResponse(user);
    }
}
