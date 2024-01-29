package br.com.fiap.fiaplus.repository;

import br.com.fiap.fiaplus.document.Favorite;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends ReactiveMongoRepository<Favorite, String> {
}
