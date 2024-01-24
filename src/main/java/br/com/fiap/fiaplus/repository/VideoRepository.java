package br.com.fiap.fiaplus.repository;

import br.com.fiap.fiaplus.document.Video;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface VideoRepository extends ReactiveMongoRepository<Video, String> {

    Mono<Video> findByTitle(String title);
}