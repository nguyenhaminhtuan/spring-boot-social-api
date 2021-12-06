package io.github.nguyenhaminhtuan.springbootsocialapi.controller;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.RegisterUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.UserResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        UserResponse response = registrationService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
