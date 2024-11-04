package culturemedia;

import culturemedia.model.Video;
import culturemedia.model.Reproduccion;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import java.util.ArrayList;
import java.util.List;

public class CultureotecaServiceImpl implements CultureotecaService {
    private final VideoRepository videoRepository;
    private final ViewsRepository viewsRepository;

    // Constructor que inyecta los repositorios
    public CultureotecaServiceImpl(VideoRepository videoRepository, ViewsRepository viewsRepository) {
        this.videoRepository = videoRepository;
        this.viewsRepository = viewsRepository;
    }

    @Override
    public List<Video> listarTodos() {
        return videoRepository.findAll();
    }

    @Override
    public Video agregar(Video video) {
        videoRepository.save(video);
        return video;
    }

    @Override
    public Reproduccion agregar(Reproduccion reproduccion) {
        viewsRepository.agregar(reproduccion);
        return reproduccion;
    }
}
