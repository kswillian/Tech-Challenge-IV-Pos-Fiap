package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.document.enums.Category;
import br.com.fiap.fiaplus.mapper.VideoMapper;
import br.com.fiap.fiaplus.model.VideoRequest;
import br.com.fiap.fiaplus.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;


import java.time.LocalDateTime;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VideoServiceImplTest {
    @Mock
    private VideoRepository videoRepository;
    @Mock
    private VideoMapper videoMapper;
    @InjectMocks
    private VideoServiceImpl videoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create Video - Should create and return 'Inception' video")
    void testCreate() {
        String title = "Inception";
        String description = "Mind-bending thriller";
        Category category = Category.THRILLER;
        String url = "http://example.com/inception";
        LocalDateTime now = LocalDateTime.now();

        VideoRequest request = new VideoRequest(title, description, url, category);
        Video video = new Video(null, title, description, category, url, now);

        when(videoMapper.toEntity(request)).thenReturn(video);
        when(videoRepository.save(video)).thenReturn(Mono.just(video));

        Mono<Video> result = videoServiceImpl.create(request);

        Video resultVideo = result.block();
        assertNotNull(resultVideo);
        assertEquals(title, resultVideo.getTitle());
        assertEquals(description, resultVideo.getDescription());
        assertEquals(category, resultVideo.getCategory());
        assertEquals(url, resultVideo.getUrl());
        assertNotNull(resultVideo.getDateRegister());
    }

    @Test
    @DisplayName("List Video by ID - Should retrieve 'Jurassic Park'")
    void testListById() {
        String id = "video_id";
        Video video = new Video(id, "Jurassic Park", "Adventure and dinosaurs", Category.ADVENTURE, "http://example.com/jurassic", LocalDateTime.now());

        when(videoRepository.findById(id)).thenReturn(Mono.just(video));

        Mono<Video> result = videoServiceImpl.listById(id);

        assertNotNull(result.block());
        assertEquals("Jurassic Park", result.block()
                .getTitle());
    }

    @Test
    @DisplayName("Update Video - Should update and return 'Avatar' with new details")
    void testUpdate() {
        String id = "video_id";
        VideoRequest request = new VideoRequest("Avatar", "Epic science fiction", "http://example.com/avatar", Category.ADVENTURE);
        Video existingVideo = new Video(id, "Avatar", "Old description", Category.ADVENTURE, "http://example.com/avatar", LocalDateTime.now());
        Video updatedVideo = new Video(id, "Avatar", "Epic science fiction", Category.ADVENTURE, "http://example.com/avatar", LocalDateTime.now());

        when(videoRepository.findById(id)).thenReturn(Mono.just(existingVideo));
        when(videoMapper.toEntity(request, existingVideo)).thenReturn(updatedVideo);
        when(videoRepository.save(updatedVideo)).thenReturn(Mono.just(updatedVideo));

        Mono<Video> result = videoServiceImpl.update(id, request);

        assertNotNull(result.block());
        assertEquals("Avatar", result.block()
                .getTitle());
        assertEquals("Epic science fiction", result.block()
                .getDescription());
    }

    @Test
    @DisplayName("Delete Video by ID - Should remove 'Star Wars' by its ID")
    void testDeleteById() {
        String id = "video_id";
        Video video = new Video(id, "Star Wars", "Space opera", Category.ADVENTURE, "http://example.com/starwars", LocalDateTime.now());

        when(videoRepository.findById(id)).thenReturn(Mono.just(video));
        when(videoRepository.delete(video)).thenReturn(Mono.empty());

        videoServiceImpl.deleteById(id);

        verify(videoRepository).delete(video);
    }
}
