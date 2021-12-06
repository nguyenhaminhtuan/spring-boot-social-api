package io.github.nguyenhaminhtuan.springbootsocialapi.controller;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.LoginUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.LoginResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(authenticationService.loginUser(request));
    }
}
