package io.github.nguyenhaminhtuan.springbootsocialapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @NotBlank
    @Email
    private String username;

    @Column
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "cover_photo_url")
    private String coverPhotoUrl;

    @Column(name = "first_name")
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Column(columnDefinition = "int2")
    @Enumerated(EnumType.ORDINAL)
    private UserRole role = UserRole.USER;

    @Column(columnDefinition = "int2")
    @Enumerated(EnumType.ORDINAL)
    private UserGender gender;

    @Column(name = "date_of_birth")
    @Past
    private LocalDate dateOfBirth;

    @Column
    private boolean enabled = true;

    @Column
    private boolean locked = false;
}
