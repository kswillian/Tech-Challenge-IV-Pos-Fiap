package br.com.fiap.fiaplus.controller;

import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.model.VideoRequest;
import br.com.fiap.fiaplus.service.VideoService;
import com.mongodb.client.result.DeleteResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/videos")
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<Mono<Video>> create(@RequestBody @Valid VideoRequest video){
        log.info("[VideoController] - create");
        return ResponseEntity.status(CREATED)
                .body(videoService.create(video));
    }

    @GetMapping
    public ResponseEntity<Flux<Video>> listAll(){
        log.info("[VideoController] - listAll");
        return ResponseEntity.status(OK)
                .body(videoService.listAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Video>> listById(@PathVariable String id){
        log.info("[VideoController] - listById");
        return ResponseEntity.status(OK)
                .body(videoService.listById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Mono<Video>> updateById(@PathVariable String id, @RequestBody @Valid VideoRequest video){
        log.info("[VideoController] - updateById");
        return ResponseEntity.status(OK)
                .body(videoService.update(id, video));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        log.info("[VideoController] - listById");
        videoService.deleteById(id);
        return ResponseEntity.status(OK).build();
    }

}