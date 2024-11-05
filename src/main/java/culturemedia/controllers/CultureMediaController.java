package culturemedia.controllers;

import java.util.List;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CulturemediaService;

public class CultureMediaController {

    private final CulturemediaService culturemediaService;

    // Constructor que recibe el servicio CulturemediaService
    public CultureMediaController(CulturemediaService culturemediaService) {
        this.culturemediaService = culturemediaService;
    }

    // Método para listar todos los videos, lanzará VideoNotFoundException si no se encuentran videos
    public List<Video> findAllVideos() throws VideoNotFoundException {
        return culturemediaService.findAll(); // Llama al método findAll del servicio
    }

    // Método para agregar un nuevo video a través del servicio
    public Video agregarVideo(Video video) {
        return culturemediaService.save(video); // Llama al método save del servicio
    }
}
