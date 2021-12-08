package io.github.nguyenhaminhtuan.springbootsocialapi.security;

import io.github.nguyenhaminhtuan.springbootsocialapi.model.User;
import io.github.nguyenhaminhtuan.springbootsocialapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username {}", username);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }

        log.debug("Found {}", user.get());
        return new UserPrincipal(user.get());
    }
}
