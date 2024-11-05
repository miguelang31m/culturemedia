package culturemedia.repository.impl;

import java.util.ArrayList;
import java.util.List;

import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;

public class VideoRepositoryImpl implements VideoRepository {

    private final List<Video> videos;

    public VideoRepositoryImpl() {
        videos = new ArrayList<>();
    }

    @Override
    public List<Video> findAll() {
        return videos;
    }

    @Override
    public Video save(Video video) {
        this.videos.add(video);
        return video;
    }

    @Override
    public List<Video> buscarPorTitulo(String titulo) {  // Implementación de buscarPorTitulo
        List<Video> filteredVideos = new ArrayList<>();
        for (Video video : videos) {
            if (video.getTitulo().contains(titulo)) {  // Usamos contains para coincidencias parciales
                filteredVideos.add(video);
            }
        }
        return filteredVideos;
    }

    @Override
    public List<Video> buscarPorDuracion(Double duracionDesde, Double duracionHasta) {  // Implementación de buscarPorDuracion
        List<Video> filteredVideos = new ArrayList<>();
        for (Video video : videos) {
            if (video.getDuracion() >= duracionDesde && video.getDuracion() <= duracionHasta) {  // Incluye límites
                filteredVideos.add(video);
            }
        }
        return filteredVideos;
    }
}