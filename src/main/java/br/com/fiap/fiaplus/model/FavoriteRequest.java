package br.com.fiap.fiaplus.model;

import jakarta.validation.constraints.NotBlank;

public record FavoriteRequest(

        @NotBlank(message = "must not be null or empty")
        String userId,
        @NotBlank(message = "must not be null or empty")
        String videoId

) {
}