package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.Reproduccion;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

import java.util.ArrayList;
import java.util.List;

public class CulturemediaService {

    private final VideoRepository videoRepository;
    private final ViewsRepository viewsRepository;

    public CulturemediaService(VideoRepository videoRepository, ViewsRepository viewsRepository) {
        this.videoRepository = videoRepository;
        this.viewsRepository = viewsRepository;
    }

    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return videos;
    }

    public List<Video> findByTitle(String title) throws VideoNotFoundException {
        List<Video> filteredVideos = new ArrayList<>();
        for (Video video : videoRepository.findAll()) {
            if (video.getTitulo().toLowerCase().contains(title.toLowerCase())) {
                filteredVideos.add(video);
            }
        }
        if (filteredVideos.isEmpty()) {
            throw new VideoNotFoundException(title);
        }
        return filteredVideos;
    }

    public List<Video> findByDuration(Double fromDuration, Double toDuration) throws VideoNotFoundException {
        List<Video> filteredVideos = new ArrayList<>();
        for (Video video : videoRepository.findAll()) {
            if (video.getDuracion() >= fromDuration && video.getDuracion() <= toDuration) {
                filteredVideos.add(video);
            }
        }
        if (filteredVideos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return filteredVideos;
    }

    public void save(Video video) {
        videoRepository.save(video);
    }

    public void save(Reproduccion reproduccion) {
        viewsRepository.agregar(reproduccion);
    }
}
