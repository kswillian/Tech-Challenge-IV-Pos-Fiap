package br.com.fiap.fiaplus.controller;

import br.com.fiap.fiaplus.document.Favorite;
import br.com.fiap.fiaplus.document.User;
import br.com.fiap.fiaplus.model.FavoriteCriteria;
import br.com.fiap.fiaplus.model.FavoriteRequest;
import br.com.fiap.fiaplus.model.UserCriteria;
import br.com.fiap.fiaplus.model.UserRequest;
import br.com.fiap.fiaplus.service.FavoriteServiceImpl;
import br.com.fiap.fiaplus.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/favorites")
public class FavoriteController {

    private final FavoriteServiceImpl favoriteService;

    @PostMapping
    public ResponseEntity<Mono<Favorite>> create(@RequestBody @Valid FavoriteRequest request){
        log.info("[UserController] - create");
        return ResponseEntity.status(CREATED)
                .body(favoriteService.create(request));
    }

    @GetMapping
    public ResponseEntity<Mono<PageImpl<Favorite>>> listAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String direction, FavoriteCriteria favoriteCriteria){

        log.info("[UserController] - listAll");

        var pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "dateAdded");

        return ResponseEntity.status(OK)
                .body(favoriteService.listAll(pageRequest, favoriteCriteria));

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Favorite>> listById(@PathVariable String id){
        log.info("[FavoriteController] - listById");
        return ResponseEntity.status(OK)
                .body(favoriteService.listById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Mono<Favorite>> updateById(@PathVariable String id, @RequestBody @Valid FavoriteRequest request){
        log.info("[FavoriteController] - updateById");
        return ResponseEntity.status(OK)
                .body(favoriteService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        log.info("[UserController] - listById");
        favoriteService.deleteById(id);
        return ResponseEntity.status(OK).build();
    }

}