package culturemedia.repository;

import culturemedia.model.Video;
import culturemedia.repository.impl.VideoRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoRepositoryTest {

    private VideoRepository videoRepository;

    @BeforeEach
    void init() {
        videoRepository = new VideoRepositoryImpl();

        // Añadimos videos específicos para las pruebas
        List<Video> videos = List.of(
                new Video("01", "Título 1", "Descripción 1", 4.5),
                new Video("02", "Título 2", "Descripción 2", 5.5),
                new Video("03", "Título 3", "Descripción 3", 4.4),
                new Video("04", "Título 4", "Descripción 4", 3.5),
                new Video("05", "Clic 5", "Descripción 5", 5.7),
                new Video("06", "Clic 6", "Descripción 6", 5.1)
        );

        // Guardamos todos los videos en el repositorio
        for (Video video : videos) {
            videoRepository.save(video);
        }
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        List<Video> videos = videoRepository.findAll();
        assertEquals(6, videos.size()); // Esperamos 6 videos en total
    }

    @Test
    void when_FindByTitle_only_videos_which_contains_the_word_in_the_title_should_be_returned_successfully() {
        List<Video> videos = videoRepository.buscarPorTitulo("Clic");  // Usamos buscarPorTitulo
        assertEquals(2, videos.size()); // Esperamos 2 videos que contienen "Clic" en el título
    }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_successfully() {
        List<Video> videos = videoRepository.buscarPorDuracion(4.5, 5.5);  // Usamos buscarPorDuracion
        assertEquals(3, videos.size()); // Esperamos 3 videos en el rango de duración 4.5 a 5.5
    }

    @Test
    void when_FindByTitle_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
        List<Video> videos = videoRepository.buscarPorTitulo("NoMatch");  // Usamos buscarPorTitulo
        assertEquals(0, videos.size()); // Esperamos 0 videos si no hay coincidencias
    }

    @Test
    void when_FindByDuration_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
        List<Video> videos = videoRepository.buscarPorDuracion(6.0, 7.0);  // Usamos buscarPorDuracion
        assertEquals(0, videos.size()); // Esperamos 0 videos si no hay coincidencias en el rango
    }
}
