package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.Reproduccion;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

import java.util.List;

public class CulturemediaService {

    private final VideoRepository videoRepository;
    private final ViewsRepository viewsRepository;

    public CulturemediaService(VideoRepository videoRepository, ViewsRepository viewsRepository) {
        this.videoRepository = videoRepository;
        this.viewsRepository = viewsRepository;
    }

    // Método para obtener todos los videos o lanzar excepción si no hay ninguno
    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return videos;
    }

    // Método para encontrar videos por título
    public List<Video> findByTitle(String title) throws VideoNotFoundException {
        List<Video> videos = videoRepository.buscarPorTitulo(title);
        if (videos == null || videos.isEmpty()) {
            throw new VideoNotFoundException(title);
        }
        return videos;
    }

    // Otros métodos del servicio, como guardar video y reproducciones
    public void save(Video video) {
        videoRepository.save(video);
    }

    public void save(Reproduccion reproduccion) {
        viewsRepository.agregar(reproduccion);
    }
}
