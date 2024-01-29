package br.com.fiap.fiaplus.model;

import br.com.fiap.fiaplus.document.User;
import br.com.fiap.fiaplus.document.Video;
import jakarta.validation.constraints.NotBlank;

public record FavoriteRequest(


        User user,

        Video video

) {
}