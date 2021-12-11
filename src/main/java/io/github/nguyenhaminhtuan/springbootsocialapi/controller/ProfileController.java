package io.github.nguyenhaminhtuan.springbootsocialapi.controller;

import io.github.nguyenhaminhtuan.springbootsocialapi.security.UserPrincipal;
import io.github.nguyenhaminhtuan.springbootsocialapi.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("{username}")
    public void getProfileByUsername(@PathVariable("username") String username) {

    }

    @PostMapping("{username}/follow")
    public void followProfile(
            @PathVariable("username") String username,
            @AuthenticationPrincipal UserPrincipal principal) {
        profileService.followProfile(principal.getUsername(), username);
    }

    @DeleteMapping("{username}/follow")
    public void unfollowProfile(
            @PathVariable("username") String username,
            @AuthenticationPrincipal UserPrincipal principal) {
        profileService.unfollowProfile(principal.getUsername(), username);

    }
}
