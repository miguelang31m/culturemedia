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
                List<Video> result = new ArrayList<>();
                for (Video video : videos) {
                    if (video.getDuracion() >= duracionDesde && video.getDuracion() <= duracionHasta) {
                        result.add(video);
                    }
                }
                return result;
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
    void when_FindByTitle_videos_with_the_title_should_be_returned_successfully() throws VideoNotFoundException {
        videoRepository.save(new Video("01", "Clic 1", "Descripción 1", 4.5));
        videoRepository.save(new Video("02", "Clic 2", "Descripción 2", 5.5));

        List<Video> videos = culturemediaService.findByTitle("Clic");
        assertEquals(2, videos.size());
        assertEquals("Clic 1", videos.get(0).getTitulo());
        assertEquals("Clic 2", videos.get(1).getTitulo());
    }

    @Test
    void when_FindByTitle_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            culturemediaService.findByTitle("Inexistente");
        });
        assertEquals("Video not found by title: Inexistente", exception.getMessage());
    }

    @Test
    void when_FindByDuration_videos_in_the_duration_range_should_be_returned_successfully() throws VideoNotFoundException {
        videoRepository.save(new Video("01", "Título 1", "Descripción 1", 4.5));
        videoRepository.save(new Video("02", "Título 2", "Descripción 2", 5.5));
        videoRepository.save(new Video("03", "Clic 3", "Descripción 3", 5.1));

        List<Video> videos = culturemediaService.findByDuration(4.5, 5.5);
        assertEquals(3, videos.size());
        assertEquals("Título 1", videos.get(0).getTitulo());
        assertEquals("Título 2", videos.get(1).getTitulo());
        assertEquals("Clic 3", videos.get(2).getTitulo());
    }

    @Test
    void when_FindByDuration_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            culturemediaService.findByDuration(1.0, 2.0);
        });
        assertEquals("Video not found.", exception.getMessage());
    }
}
