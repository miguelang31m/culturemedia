package culturemedia.repository.impl;

import java.util.ArrayList;
import java.util.List;

import culturemedia.model.Reproduccion;
import culturemedia.repository.ViewsRepository;
import org.springframework.stereotype.Component;

@Component // Permite que Spring lo detecte como un bean
public class ViewsRepositoryImpl implements ViewsRepository {

    private final List<Reproduccion> reproducciones;

    public ViewsRepositoryImpl() {
        this.reproducciones = new ArrayList<>();
    }

    @Override
    public Reproduccion agregar(Reproduccion reproduccion) {  // Implementación del método agregar
        this.reproducciones.add(reproduccion);
        return reproduccion;
    }
}
