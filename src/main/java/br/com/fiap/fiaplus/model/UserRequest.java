package br.com.fiap.fiaplus.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
        @NotBlank(message = "must not be null or empty")
        String name,

        @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
        @NotBlank(message = "must not be null or empty")
        @Email(message = "must be a valid email")
        String email

) {
}