package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.builder.CriteriaBuilder;
import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.document.enums.Category;
import br.com.fiap.fiaplus.mapper.VideoMapper;
import br.com.fiap.fiaplus.model.VideoCriteria;
import br.com.fiap.fiaplus.model.VideoRequest;
import br.com.fiap.fiaplus.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static br.com.fiap.fiaplus.util.Utils.handleNotFound;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService<Video, VideoRequest> {

    private final ReactiveMongoTemplate mongoTemplate;
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final ResourceLoader resourceLoader;
    private static final String FORMAT = "classpath:videos/%s.mp4";

    @Override
    public Mono<Video> create(VideoRequest request) {
        log.info("[VideoService] - create");
        var video = videoMapper.toEntity(request);
        video.setDateRegister(LocalDateTime.now());
        return videoRepository.save(video);
    }

    @Override
    public Mono<PageImpl<Video>> listAll(Pageable pageable, Object criteria) {

        log.info("[VideoService] - listAll");
        Query query = new Query()
                .addCriteria(CriteriaBuilder.buildVideoCriteria((VideoCriteria) criteria))
                .with(pageable);

        Flux<Video> videos = mongoTemplate.find(query, Video.class);

        return videos.collectList()
                .zipWith(videoRepository.count())
                .map(video -> new PageImpl<>(video.getT1(), pageable, video.getT2()));
    }

    @Override
    public Mono<PageImpl<Video>> listAllByCategory(Pageable pageable, VideoCriteria criteria, Category category) {
        log.info("[VideoService] - listAllByCategory");

        Query query = new Query()
                .addCriteria(CriteriaBuilder.buildVideoCriteria(criteria))
                .addCriteria(Criteria.where("category").is(category))
                .with(pageable);

        Flux<Video> videos = mongoTemplate.find(query, Video.class);

        return videos.collectList()
                .map(videoList -> new PageImpl<>(videoList, pageable, videoList.size()));
    }

    @Override
    public Mono<PageImpl<Video>> listAllByDate(Pageable pageable, VideoCriteria criteria, String timestamp) {
        log.info("[VideoService] - listAllByDate");
        LocalDateTime startOfDay = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(timestamp)), ZoneId.systemDefault()).toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
        System.out.println(startOfDay);
        System.out.println(endOfDay);
        Query query = new Query()
                .addCriteria(CriteriaBuilder.buildVideoCriteria(criteria))
                .addCriteria(Criteria.where("dateRegister").gte(startOfDay).lte(endOfDay))
                .with(pageable);

        Flux<Video> videos = mongoTemplate.find(query, Video.class);

        return videos.collectList()
                .map(videoList -> new PageImpl<>(videoList, pageable, videoList.size()));
    }

    @Override
    public Mono<Video> listById(String id) {
        log.info("[VideoService] - listById");
        return handleNotFound(videoRepository.findById(id), id);
    }

    @Override
    public Mono<Video> findByTitle(String title) {
        log.info("[VideoService] - listByTitle");
        return handleNotFound(videoRepository.findByTitle(title), title);
    }

    @Override
    public Mono<Video> update(String id, VideoRequest request) {
        log.info("[VideoService] - update");
        return listById(id)
                .map(video -> videoMapper.toEntity(request, video))
                .flatMap(videoRepository::save);
    }

    @Override
    public void deleteById(String id) {
        log.info("[VideoService] - deleteById");
        var video = handleNotFound(videoRepository.findById(id), id);
        videoRepository.delete(video.block()).block();
    }

    // TODO pensar na implementacao
    public Mono<String> upload(FilePart video){

        // Obtenha o nome do arquivo do vídeo
        String fileName = video.filename();

        // Obtenha o diretório de recursos
        File resourceDirectory = new File("src/main/resources");

        // Crie o caminho completo do arquivo na pasta "videos" dentro do diretório de recursos
        String filePath = resourceDirectory.getAbsolutePath() + "/videos/" + fileName;
        System.out.println("Caminho do arquivo: " + filePath);

        // Salve o vídeo no local especificado
        return video.transferTo(new File(filePath))
                .then(Mono.just(filePath));
    }

    // TODO pensar na implementacao
    public Flux<Video> streaming(String id){
        return Flux.just(new Video());
    }

    public Mono<Resource> getVideo(String title){
        return Mono.fromSupplier(()->resourceLoader.
                getResource(String.format(FORMAT,title)))   ;
    }
}