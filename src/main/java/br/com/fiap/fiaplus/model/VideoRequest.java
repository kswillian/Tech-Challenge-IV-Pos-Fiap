package br.com.fiap.fiaplus.model;

import br.com.fiap.fiaplus.document.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VideoRequest (

        @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
        @NotBlank(message = "must not be null or empty")
        String title,

        @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
        @NotBlank(message = "must not be null or empty")
        String description,

        @Size(min = 3, max = 150, message = "must be between 3 and 150 characters")
        String url,

        Category category
) {

}