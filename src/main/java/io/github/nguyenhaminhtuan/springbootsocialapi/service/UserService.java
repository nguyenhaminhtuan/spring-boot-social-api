package io.github.nguyenhaminhtuan.springbootsocialapi.service;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.LoginUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.RegisterUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.UpdateUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.LoginResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.UserResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.exception.ResourceNotFoundException;
import io.github.nguyenhaminhtuan.springbootsocialapi.exception.UsernameAlreadyExistsException;
import io.github.nguyenhaminhtuan.springbootsocialapi.mapper.UserMapper;
import io.github.nguyenhaminhtuan.springbootsocialapi.model.User;
import io.github.nguyenhaminhtuan.springbootsocialapi.model.UserRole;
import io.github.nguyenhaminhtuan.springbootsocialapi.repository.UserRepository;
import io.github.nguyenhaminhtuan.springbootsocialapi.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse loginUser(LoginUserRequest request) {
        log.debug("Logging in user with credentials {}", request);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        log.info("Username {} with authorities {} login at {}",
                principal.getUsername(), principal.getAuthorities(), LocalDateTime.now());
        return new LoginResponse(authentication.isAuthenticated(), principal.getUsername());
    }

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

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Not found user with id %d", id)));
        return mapper.toUserResponse(user);
    }

    @PreAuthorize("#id == authentication.principal.id")
    public void updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Not found user with id %d", id)));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setDateOfBirth(request.getDateOfBirth());

        userRepository.save(user);
    }

    @PreAuthorize("#id == authentication.principal.id")
    public void updateUserAvatar(Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @PreAuthorize("#id == authentication.principal.id")
    public void updateUserCoverPhoto(Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
