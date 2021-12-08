package io.github.nguyenhaminhtuan.springbootsocialapi.controller;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.UpdateUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.security.UserPrincipal;
import io.github.nguyenhaminhtuan.springbootsocialapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CurrentUserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(userService.getUserById(principal.getId()));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCurrentUser(
            @Valid @RequestBody UpdateUserRequest request, @AuthenticationPrincipal UserPrincipal principal) {
        userService.updateUser(principal.getId(), request);
    }

    @PatchMapping("/avatar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCurrentUserAvatar(@AuthenticationPrincipal UserPrincipal principal) {
        userService.updateUserAvatar(principal.getId());
    }

    @PatchMapping("/cover-photo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatCurrentUserCoverPhoto(@AuthenticationPrincipal UserPrincipal principal) {
        userService.updateUserCoverPhoto(principal.getId());
    }
}
