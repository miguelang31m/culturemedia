package culturemedia.repository;

import culturemedia.model.Video;
import java.util.List;

public interface VideoRepository {
    List<Video> findAll();
    void save(Video video);
    Video findByTitle(String title);
}
