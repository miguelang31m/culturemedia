package culturemedia.controllers;

import java.util.*;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CulturemediaService;

public class CultureMediaController {

    private final CulturemediaService culturemediaService;

    public CultureMediaController(CulturemediaService culturemediaService) {
        this.culturemediaService = culturemediaService;
    }

    // Método para obtener todos los videos a través del servicio
    public List<Video> findAllVideos() throws VideoNotFoundException {
        return culturemediaService.findAll();
    }

    // Método para agregar un nuevo video a través del servicio y devolverlo
    public Video agregarVideo(Video video) {
        return culturemediaService.save(video); // Llama al método save del servicio
    }
}
