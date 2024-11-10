package culturemedia.controllers;

import java.util.List;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CulturemediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Anotación para definir el controlador como REST
@RequestMapping("/videos") // Mapea las solicitudes a "/videos"
public class CultureMediaController {

    private final CulturemediaService culturemediaService;

    // Constructor que recibe el servicio CulturemediaService
    @Autowired
    public CultureMediaController(CulturemediaService culturemediaService) {
        this.culturemediaService = culturemediaService;
    }

    // Método para listar todos los videos, lanzará VideoNotFoundException si no se encuentran videos
    @GetMapping // Define que este método responde a solicitudes GET
    public List<Video> findAllVideos() throws VideoNotFoundException {
        return culturemediaService.findAll(); // Llama al método findAll del servicio
    }

    // Método para agregar un nuevo video a través del servicio
    @PostMapping // Define que este método responde a solicitudes POST
    public Video agregarVideo(@RequestBody Video video) { // Usa @RequestBody para obtener el JSON enviado
        culturemediaService.save(video); // Guarda el video
        return video; // Devuelve el video guardado
    }
}
