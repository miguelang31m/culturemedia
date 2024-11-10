package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.Reproduccion;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Anotación para que Spring gestione esta clase como un bean de servicio
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

    // Método para encontrar videos por duración en un rango específico
    public List<Video> findByDuration(double fromDuration, double toDuration) throws VideoNotFoundException {
        List<Video> videos = videoRepository.buscarPorDuracion(fromDuration, toDuration);
        if (videos == null || videos.isEmpty()) {
            throw new VideoNotFoundException("No videos found within the specified duration range.");
        }
        return videos;
    }

    // Método para guardar un video y devolverlo
    public Video save(Video video) {
        return videoRepository.save(video); // Ahora retorna el video guardado
    }

    public void save(Reproduccion reproduccion) {
        viewsRepository.agregar(reproduccion);
    }
}
