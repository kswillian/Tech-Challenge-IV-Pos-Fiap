package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public Mono<Video> create(Video video){
        log.info("[VideoService] - create");
        return videoRepository.save(video);
    }

    public Flux<Video> listAll(){
        log.info("[VideoService] - listAll");
        return videoRepository.findAll();
    }

    public Mono<Video> listById(String id){
        log.info("[VideoService] - listById");
        return videoRepository.findById(id);
    }

    public Mono<Video> update(Video video){
        log.info("[VideoService] - update");
        return videoRepository.save(video);
    }

    public void deleteById(String id){
        log.info("[VideoService] - deleteById");
        videoRepository.findEndRemove(id);
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