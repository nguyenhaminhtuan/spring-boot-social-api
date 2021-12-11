package io.github.nguyenhaminhtuan.springbootsocialapi.service;

import io.github.nguyenhaminhtuan.springbootsocialapi.exception.ResourceNotFoundException;
import io.github.nguyenhaminhtuan.springbootsocialapi.model.User;
import io.github.nguyenhaminhtuan.springbootsocialapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    public void followProfile(String usernameFollower, String usernameFollowing) {
        User follower = userRepository.findByUsernameAndLockedFalse(usernameFollower)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Not found username %s", usernameFollower)));
        User following = userRepository.findByUsernameAndLockedFalse(usernameFollowing)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Not found username %s", usernameFollowing)));
        Set<User> followingList = follower.getFollowing();
        if (!followingList.contains(following)) {
            followingList.add(following);
            userRepository.save(follower);
        }
    }

    @PreAuthorize("isAuthenticated()")
    public void unfollowProfile(String usernameFollower, String usernameFollowing) {
        User follower = userRepository.findByUsername(usernameFollower)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Not found username %s", usernameFollower)));
        User following = userRepository.findByUsername(usernameFollowing)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Not found username %s", usernameFollowing)));
        Set<User> followingList = follower.getFollowing();
        if (followingList.contains(follower)) {
            followingList.remove(following);
            userRepository.save(follower);
        }
    }
}
