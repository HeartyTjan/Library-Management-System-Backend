package com.swifthearty.libraryms.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateNewUserRequest {
    @Valid

    @NotNull(message = "firstName is required")
    @NotBlank(message = "firstName Cannot be blank")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotBlank(message = "lastName Cannot be blank")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 1, message = "Phone number is required")
    @Pattern(regexp = "^(234|0)(70|80|81|90|91)\\d{8}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotNull(message = "password is required")
    @NotBlank(message = "password Cannot be blank")
    @Size(min = 4, max = 15, message = "Password must not be below 5 and above 15")
    private String password;

    @NotNull(message = "Username is required")
    @NotBlank(message = "Username Cannot be blank")
    private String username;

    @NotNull(message = "Role is required")
    @NotBlank(message = "Role is required")
    private String role;

}
