package io.github.nguyenhaminhtuan.springbootsocialapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginUserRequest {
    @NotBlank
    @Email
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
}
