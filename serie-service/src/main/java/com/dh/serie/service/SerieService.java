package com.dh.serie.service;

import com.dh.serie.api.queue.SerieSender;
import com.dh.serie.model.Serie;
import com.dh.serie.repository.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class SerieService {

    private final SerieRepository repository;

    private final SerieSender sender;

    public List<Serie> getAll() {
        return repository.findAll();
    }


    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public void createSerie(Serie serie) {
        sender.sendSerie(serie);
        repository.save(serie);
    }


}
