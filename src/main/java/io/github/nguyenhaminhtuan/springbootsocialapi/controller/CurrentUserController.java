package io.github.nguyenhaminhtuan.springbootsocialapi.controller;

import io.github.nguyenhaminhtuan.springbootsocialapi.security.CurrentUser;
import io.github.nguyenhaminhtuan.springbootsocialapi.security.UserPrincipal;
import io.github.nguyenhaminhtuan.springbootsocialapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CurrentUserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal principal) {
        return ResponseEntity.ok(userService.getUserById(principal.getId()));
    }
}
