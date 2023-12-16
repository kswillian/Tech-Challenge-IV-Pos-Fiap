package br.com.fiap.fiaplus.repository;

import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/video")
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<Mono<Video>> create(@RequestBody Video video){
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

}