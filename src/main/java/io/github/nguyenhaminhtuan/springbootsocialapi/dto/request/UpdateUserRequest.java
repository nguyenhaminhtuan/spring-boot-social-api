package io.github.nguyenhaminhtuan.springbootsocialapi.dto.request;

import io.github.nguyenhaminhtuan.springbootsocialapi.model.Gender;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    private Gender gender;

    @Past
    private LocalDate dateOfBirth;
}
