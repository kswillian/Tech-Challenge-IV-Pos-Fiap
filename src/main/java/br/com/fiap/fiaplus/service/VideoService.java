package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.document.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class VideoService {

    // TODO Implementar injeção do repository, implementar crud basico e remover o mock
    private Video mockVideo;

    public VideoService() {
        mockVideo = Video.builder()
                .id(UUID.randomUUID())
                .title("title mock")
                .description("description mock")
                .url("url mock")
                .dateRegister(LocalDateTime.now())
                .build();
    }

    public Mono<Video> create(Video video){
        log.info("[VideoService] - create");
        return Mono.just(mockVideo);
    }

    public Flux<Video> listAll(){
        log.info("[VideoService] - listAll");
        return Flux.just(mockVideo);
    }

    public Mono<Video> listById(String id){
        log.info("[VideoService] - listById");
        return Mono.just(mockVideo);
    }

    public Mono<Video> update(Video video){
        log.info("[VideoService] - update");
        return Mono.just(mockVideo);
    }

    public void deleteById(String id){
        log.info("[VideoService] - deleteById");
    }

    // TODO pensar na implementacao
    public Mono<Video> upload(){
        return Mono.just(Video.builder().build());
    }

    // TODO pensar na implementacao
    public Flux<Video> streaming(String id){
        return Flux.just(Video.builder().build());
    }

}