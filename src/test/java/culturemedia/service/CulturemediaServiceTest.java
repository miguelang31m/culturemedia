package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.Reproduccion;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CulturemediaServiceTest {

    private CulturemediaService culturemediaService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ViewsRepository viewsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        culturemediaService = new CulturemediaService(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        when(videoRepository.findAll()).thenReturn(List.of()); // Simula un repositorio vacío

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> culturemediaService.findAll());
        assertEquals("Video not found.", exception.getMessage());
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        List<Video> videos = List.of(
                new Video("01", "Título 1", "Descripción 1", 4.5),
                new Video("02", "Título 2", "Descripción 2", 5.5)
        );

        when(videoRepository.findAll()).thenReturn(videos);

        List<Video> result = culturemediaService.findAll();
        assertEquals(2, result.size());
        assertEquals("Título 1", result.get(0).getTitulo());
        assertEquals("Título 2", result.get(1).getTitulo());
    }

    @Test
    void when_FindByTitle_videos_with_the_title_should_be_returned_successfully() throws VideoNotFoundException {
        List<Video> videos = List.of(
                new Video("05", "Clic 5", "Descripción 5", 5.7),
                new Video("06", "Clic 6", "Descripción 6", 5.1)
        );

        when(videoRepository.buscarPorTitulo("Clic")).thenReturn(videos);

        List<Video> result = culturemediaService.findByTitle("Clic");
        assertEquals(2, result.size());
        assertEquals("Clic 5", result.get(0).getTitulo());
        assertEquals("Clic 6", result.get(1).getTitulo());
    }

    @Test
    void when_FindByTitle_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        when(videoRepository.buscarPorTitulo("Inexistente")).thenReturn(List.of());

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> culturemediaService.findByTitle("Inexistente"));
        assertEquals("Video not found by title: Inexistente", exception.getMessage());
    }

    @Test
    void when_FindByDuration_videos_in_the_duration_range_should_be_returned_successfully() throws VideoNotFoundException {
        List<Video> videos = List.of(
                new Video("01", "Título 1", "Descripción 1", 4.5),
                new Video("02", "Título 2", "Descripción 2", 5.5),
                new Video("06", "Clic 6", "Descripción 3", 5.1)
        );

        when(videoRepository.buscarPorDuracion(4.5, 5.5)).thenReturn(videos);

        List<Video> result = culturemediaService.findByDuration(4.5, 5.5);
        assertEquals(3, result.size());
        assertEquals("Título 1", result.get(0).getTitulo());
        assertEquals("Título 2", result.get(1).getTitulo());
        assertEquals("Clic 6", result.get(2).getTitulo());
    }

    @Test
    void when_FindByDuration_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        when(videoRepository.buscarPorDuracion(1.0, 2.0)).thenReturn(List.of());

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> culturemediaService.findByDuration(1.0, 2.0));
        assertEquals("No videos found within the specified duration range: 1 - 2", exception.getMessage());
    }
}
