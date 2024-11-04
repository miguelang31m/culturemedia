package culturemedia.repository.impl;

import java.util.ArrayList;
import java.util.List;

import culturemedia.model.Reproduccion;
import culturemedia.repository.ViewsRepository;

public class ViewsRepositoryImpl implements ViewsRepository {

    private final List<Reproduccion> views;

    public ViewsRepositoryImpl() {
        this.views = new ArrayList<>();
    }

    @Override
    public Reproduccion save(Reproduccion view) {
        this.views.add(view);
        return view;
    }
}
