package com.notes.demo.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @Size(min = 3, max = 255, message = "The username must be between 3 and 255 characters long.")
    @NotBlank(message = "The username cannot be blank.")
    private String username;

    @Email(message = "The email address provided is in an invalid format.")
    @Size(max = 255, message = "The email address must be up to 255 characters.")
    @NotBlank(message = "The email cannot be blank.")
    private String email;

    @NotBlank(message = "The password cannot be blank.")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{4,}$",
            message = "The password must be at least 4 characters long, containing at least one uppercase letter," +
                    " one lowercase letter, one number, and one special character."
    )
    private String password;
}
