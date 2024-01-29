package br.com.fiap.fiaplus.model;

import br.com.fiap.fiaplus.document.Favorite;
import br.com.fiap.fiaplus.document.User;
import br.com.fiap.fiaplus.document.Video;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteCriteria {

    public User user;
    public Video video;

    public Favorite toFavorite() {
        return new Favorite()
                .withVideo(this.video != null ? this.video : null)
                .withUser(this.user != null ? this.user : null)
                .withDateAdded(LocalDateTime.now());

    }

}
