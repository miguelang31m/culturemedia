package culturemedia.repository;

import culturemedia.model.Video;
import java.util.List;

public interface VideoRepository {
    List<Video> findAll();
    Video save(Video video);
    List<Video> buscarPorTitulo(String titulo);  // Método en español
    List<Video> buscarPorDuracion(Double duracionDesde, Double duracionHasta); // Método en español
}
