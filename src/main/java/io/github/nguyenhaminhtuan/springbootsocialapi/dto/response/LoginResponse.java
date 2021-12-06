package io.github.nguyenhaminhtuan.springbootsocialapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean authenticated;
    private String username;
}
