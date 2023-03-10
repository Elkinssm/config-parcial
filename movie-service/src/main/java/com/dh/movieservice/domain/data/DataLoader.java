package com.dh.movieservice.domain.data;

import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.repository.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final MovieRepository repository;

    public DataLoader(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.save(new Movie(1L, "filme", "terror", "what"));
        repository.save(new Movie(2L, "borboleta", "terror", "what"));
        repository.save(new Movie(3L, "adedonha", "terror", "what"));
        repository.save(new Movie(4L, "pajero", "terror", "what"));
        repository.save(new Movie(5L, "dakar", "acao", "what"));
        repository.save(new Movie(6L, "shadow", "acao", "what"));
        repository.save(new Movie(7L, "boné", "romance", "what"));
        repository.save(new Movie(8L, "xícara", "romance", "what"));
        repository.save(new Movie(9L, "Ant-Man y la Avispa: Quantumania", "accion", "what"));
        repository.save(new Movie(10L, "Creed 3", "accion", "what"));
        repository.save(new Movie(11L, "Dragones y mazmorras", "accion", "what"));
    }
}
