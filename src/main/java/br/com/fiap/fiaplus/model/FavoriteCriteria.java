package br.com.fiap.fiaplus.model;

import br.com.fiap.fiaplus.document.Favorite;
import lombok.Data;

@Data
public class FavoriteCriteria {

    public String userId;
    public String videoId;

    public Favorite toFavorite() {
        return new Favorite();

    }

}
