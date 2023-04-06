package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.repository.SerieRepository;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final SerieRepository repository;


    public CatalogService(SerieRepository repository) {
        this.repository = repository;
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }

    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public String create(Serie serie) {
        repository.save(serie);
        return serie.getId();
    }
}
