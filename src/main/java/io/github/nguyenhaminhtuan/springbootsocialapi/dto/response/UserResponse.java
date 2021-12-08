package io.github.nguyenhaminhtuan.springbootsocialapi.dto.response;

import io.github.nguyenhaminhtuan.springbootsocialapi.model.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String coverPhotoUrl;
    private Gender gender;
    private LocalDate dateOfBirth;
}
