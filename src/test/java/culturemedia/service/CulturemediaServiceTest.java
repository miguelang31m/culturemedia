package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.Reproduccion;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CulturemediaServiceTest {

    private CulturemediaService culturemediaService;
    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;

    @BeforeEach
    void setUp() {
        videoRepository = new VideoRepository() {
            private List<Video> videos = new ArrayList<>();

            @Override
            public List<Video> findAll() {
                return videos;
            }

            @Override
            public Video save(Video video) {
                videos.add(video);
                return video;
            }

            @Override
            public List<Video> buscarPorTitulo(String titulo) {
                List<Video> result = new ArrayList<>();
                for (Video video : videos) {
                    if (video.getTitulo().contains(titulo)) {
                        result.add(video);
                    }
                }
                return result;
            }

            @Override
            public List<Video> buscarPorDuracion(Double duracionDesde, Double duracionHasta) {
                return null; // No se usa en esta prueba
            }
        };

        viewsRepository = new ViewsRepository() {
            private List<Reproduccion> reproducciones = new ArrayList<>();

            @Override
            public Reproduccion agregar(Reproduccion reproduccion) {
                reproducciones.add(reproduccion);
                return reproduccion;
            }
        };

        culturemediaService = new CulturemediaService(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        videoRepository.save(new Video("01", "Título 1", "Descripción 1", 4.5));
        videoRepository.save(new Video("02", "Título 2", "Descripción 2", 5.5));

        List<Video> videos = culturemediaService.findAll();
        assertEquals(2, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        Exception exception = assertThrows(VideoNotFoundException.class, () -> {
            culturemediaService.findAll();
        });
        assertEquals("Video not found.", exception.getMessage());
    }

    @Test
    void when_FindByTitle_videos_with_the_title_should_be_returned_successfully() throws VideoNotFoundException {
        videoRepository.save(new Video("01", "Clic 1", "Descripción 1", 4.5));
        videoRepository.save(new Video("02", "Clic 2", "Descripción 2", 5.5));

        List<Video> videos = culturemediaService.findByTitle("Clic");
        assertEquals(2, videos.size());
    }

    @Test
    void when_FindByTitle_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        Exception exception = assertThrows(VideoNotFoundException.class, () -> {
            culturemediaService.findByTitle("Inexistente");
        });
        assertEquals("Video not found by title: Inexistente", exception.getMessage());
    }
}
