package culturemedia;

import culturemedia.model.Video;
import culturemedia.model.Reproduccion;
import java.util.List;

public interface CultureotecaService {
    List<Video> listarTodos();
    Video agregar(Video video);
    Reproduccion agregar(Reproduccion reproduccion);
}
