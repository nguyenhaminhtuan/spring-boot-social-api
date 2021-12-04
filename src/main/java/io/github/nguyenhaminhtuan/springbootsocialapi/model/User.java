package io.github.nguyenhaminhtuan.springbootsocialapi.model;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String username;
    private String password;
    private String avatarUrl;
    private String coverPicture;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDateTime dateOfBirth;
    private boolean emailVerified = false;
    private boolean locked = false;
}
