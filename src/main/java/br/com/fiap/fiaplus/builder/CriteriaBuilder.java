package br.com.fiap.fiaplus.builder;

import br.com.fiap.fiaplus.document.Favorite;
import br.com.fiap.fiaplus.document.User;
import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.model.FavoriteCriteria;
import br.com.fiap.fiaplus.model.UserCriteria;
import br.com.fiap.fiaplus.model.VideoCriteria;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.query.Criteria;

@UtilityClass
public class CriteriaBuilder {

    public static Criteria buildVideoCriteria(VideoCriteria videoCriteria) {

        ExampleMatcher matcher = createMatcherVideo(videoCriteria);
        Example<Video> videoExample = Example.of(videoCriteria.toVideo(), matcher);

        return new Criteria().alike(videoExample);
    }

    public static Criteria buildUserCriteria(UserCriteria userCriteria) {

        ExampleMatcher matcher = createMatcherUser(userCriteria);
        Example<User> userExample = Example.of(userCriteria.toUser(), matcher);

        return new Criteria().alike(userExample);
    }

    public static Criteria buildFavoriteCriteria(FavoriteCriteria favoriteCriteria) {

        ExampleMatcher matcher = createMatcherFavorite(favoriteCriteria);
        Example<Favorite> favoriteExample = Example.of(favoriteCriteria.toFavorite(), matcher);

        return new Criteria().alike(favoriteExample);
    }


    private static ExampleMatcher createMatcherVideo(VideoCriteria videoCriteria) {

        return ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnorePaths(
                        videoCriteria.getDateRegister() == null ? "dateRegister" : "",
                        videoCriteria.getTitle() == null ? "title" : "",
                        "id",
                        "description",
                        "url")
                .withIgnoreCase();

    }

    private static ExampleMatcher createMatcherUser(UserCriteria userCriteria) {

        return ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnorePaths(
                        userCriteria.getDateRegister() == null ? "dateRegister" : "",
                        userCriteria.getName() == null ? "name" : "",
                        "id",
                        "name")
                .withIgnoreCase();

    }

    private static ExampleMatcher createMatcherFavorite(FavoriteCriteria favoriteCriteria) {

        return ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnorePaths(
                        "id")
                .withIgnoreCase();

    }

}
