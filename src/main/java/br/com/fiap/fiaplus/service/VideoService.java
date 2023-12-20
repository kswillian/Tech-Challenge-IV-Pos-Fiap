package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.exception.ObjectNotFoundException;
import br.com.fiap.fiaplus.mapper.VideoMapper;
import br.com.fiap.fiaplus.model.VideoRequest;
import br.com.fiap.fiaplus.repository.VideoRepository;
import com.mongodb.client.result.DeleteResult;
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
    private final VideoMapper videoMapper;

    public Mono<Video> create(final VideoRequest videoRequest){
        log.info("[VideoService] - create");
        var video = videoMapper.toEntity(videoRequest);
        return videoRepository.save(video);
    }

    public Flux<Video> listAll(){
        log.info("[VideoService] - listAll");
        return videoRepository.findAll();
    }

    public Mono<Video> listById(final String id){
        log.info("[VideoService] - listById");
        return handleNotFound(videoRepository.findById(id), id);
    }

    public Mono<Video> update(final String id, final VideoRequest videoRequest){
        log.info("[VideoService] - update");
        return listById(id)
                .map(video -> videoMapper.toEntity(videoRequest, video))
                .flatMap(videoRepository::update);
    }

    public Mono<DeleteResult> deleteById(final String id){
        log.info("[VideoService] - deleteById");
        return handleNotFound(videoRepository.findEndRemove(id), id);
    }

    // TODO pensar na implementacao
    public Mono<Video> upload(){
        return Mono.just(Video.builder().build());
    }

    // TODO pensar na implementacao
    public Flux<Video> streaming(String id){
        return Flux.just(Video.builder().build());
    }

    private <T> Mono<T> handleNotFound(Mono<T> mono, String id){

        var message = String.format(
                "Object not found. Id: %s, Type: %s", id, Video.class);

        return mono.switchIfEmpty(Mono.error(
                new ObjectNotFoundException(message)
        ));
    }

}