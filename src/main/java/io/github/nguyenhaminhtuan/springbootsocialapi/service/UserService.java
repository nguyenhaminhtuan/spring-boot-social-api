package io.github.nguyenhaminhtuan.springbootsocialapi.service;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.UpdateUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.UserResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.exception.ResourceNotFoundException;
import io.github.nguyenhaminhtuan.springbootsocialapi.mapper.UserMapper;
import io.github.nguyenhaminhtuan.springbootsocialapi.model.User;
import io.github.nguyenhaminhtuan.springbootsocialapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        return Optional.of(mapper.toUserResponse(user.get()));
    }

    @PreAuthorize("#id == authentication.principal.id")
    public void updateUser(Long id, UpdateUserRequest request) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Not found user with id %d", id));
        }

        User user = userOpt.get();
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
