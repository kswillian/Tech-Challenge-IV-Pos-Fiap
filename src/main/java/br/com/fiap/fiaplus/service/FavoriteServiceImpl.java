package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.builder.CriteriaBuilder;
import br.com.fiap.fiaplus.document.Favorite;
import br.com.fiap.fiaplus.document.User;
import br.com.fiap.fiaplus.mapper.FavoriteMapper;
import br.com.fiap.fiaplus.mapper.UserMapper;
import br.com.fiap.fiaplus.model.FavoriteCriteria;
import br.com.fiap.fiaplus.model.FavoriteRequest;
import br.com.fiap.fiaplus.model.UserCriteria;
import br.com.fiap.fiaplus.model.UserRequest;
import br.com.fiap.fiaplus.repository.FavoriteRepository;
import br.com.fiap.fiaplus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.com.fiap.fiaplus.util.Utils.handleNotFound;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements GenericService<Favorite, FavoriteRequest> {

    private final ReactiveMongoTemplate mongoTemplate;
    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapper favoriteMapper;


    @Override
    public Mono<Favorite> create(FavoriteRequest request) {
        log.info("[FavoriteService] - create");
        var video = favoriteMapper.toEntity(request);
        return favoriteRepository.save(video);
    }

    @Override
    public Mono<PageImpl<Favorite>> listAll(Pageable pageable, Object criteria) {
        log.info("[FavoriteService] - listAll");
        Query query = new Query()
                .addCriteria(CriteriaBuilder.buildFavoriteCriteria((FavoriteCriteria) criteria))
                .with(pageable);

        Flux<Favorite> favorite = mongoTemplate.find(query, Favorite.class);

        return favorite.collectList()
                .zipWith(favoriteRepository.count())
                .map(favorites -> new PageImpl<>(favorites.getT1(), pageable, favorites.getT2()));
    }

    @Override
    public Mono<Favorite> listById(String id) {
        log.info("[FavoriteService] - listById");
        return handleNotFound(favoriteRepository.findById(id), id);
    }

    @Override
    public Mono<Favorite> update(String id, FavoriteRequest request) {
        log.info("[FavoriteService] - update");
        return listById(id)
                .map(video -> favoriteMapper.toEntity(request, video))
                .flatMap(favoriteRepository::save);
    }

    @Override
    public void deleteById(String id) {
        log.info("[UserService] - deleteById");
        var user = handleNotFound(favoriteRepository.findById(id), id);
        favoriteRepository.delete(user.block()).block();
    }
}