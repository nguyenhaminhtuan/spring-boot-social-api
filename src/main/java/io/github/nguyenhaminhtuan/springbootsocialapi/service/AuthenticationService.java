package io.github.nguyenhaminhtuan.springbootsocialapi.service;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.request.LoginUserRequest;
import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.LoginResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

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
}
