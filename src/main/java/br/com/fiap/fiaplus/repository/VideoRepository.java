package br.com.fiap.fiaplus.repository;

import br.com.fiap.fiaplus.document.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class VideoRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<Video> save(final Video video){
        video.setDateRegister(LocalDateTime.now());
        return mongoTemplate.save(video);
    }

    public Mono<Video> findById(String id) {
        return mongoTemplate.findById(id, Video.class);
    }

    public Flux<Video> findAll(){
        return mongoTemplate.findAll(Video.class);
    }

    public Mono<Video> findEndRemove(String id) {
        var criteria = Criteria.where("id").is(id);
        var query = new Query().addCriteria(criteria);
        return mongoTemplate.findAndRemove(query, Video.class);

    }

}