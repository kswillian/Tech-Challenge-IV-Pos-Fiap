package br.com.fiap.fiaplus.controller;

import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.model.VideoCriteria;
import br.com.fiap.fiaplus.model.VideoRequest;
import br.com.fiap.fiaplus.service.VideoServiceImpl;
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
@RequestMapping("/v1/videos")
public class VideoController {

    private final VideoServiceImpl videoService;

    @PostMapping
    public ResponseEntity<Mono<Video>> create(@RequestBody @Valid VideoRequest request){
        log.info("[VideoController] - create");
        return ResponseEntity.status(CREATED)
                .body(videoService.create(request));
    }

    @GetMapping
    public ResponseEntity<Mono<PageImpl<Video>>> listAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String direction, VideoCriteria videoCriteria){

        log.info("[VideoController] - listAll");

        var pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "dateRegister");

        return ResponseEntity.status(OK)
                .body(videoService.listAll(pageRequest, videoCriteria));

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Video>> listById(@PathVariable String id){
        log.info("[VideoController] - listById");
        return ResponseEntity.status(OK)
                .body(videoService.listById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Mono<Video>> updateById(@PathVariable String id, @RequestBody @Valid VideoRequest request){
        log.info("[VideoController] - updateById");
        return ResponseEntity.status(OK)
                .body(videoService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        log.info("[VideoController] - listById");
        videoService.deleteById(id);
        return ResponseEntity.status(OK).build();
    }

}