package io.github.nguyenhaminhtuan.springbootsocialapi.repository;

import io.github.nguyenhaminhtuan.springbootsocialapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
