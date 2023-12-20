package br.com.fiap.fiaplus.repository;

import br.com.fiap.fiaplus.document.Video;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
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

    public Mono<Video> update(final Video video){
        return mongoTemplate.save(video);
    }

    public Mono<Video> findById(String id) {
        return mongoTemplate.findById(id, Video.class);
    }

    public Flux<Video> findAll(){
        return mongoTemplate.findAll(Video.class);
    }

    public Mono<DeleteResult> findEndRemove(String id) {
        var video = findById(id);
        return mongoTemplate.remove(video);

    }

}