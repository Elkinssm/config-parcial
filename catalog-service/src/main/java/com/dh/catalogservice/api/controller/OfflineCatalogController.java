package com.dh.catalogservice.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

public class OfflineCatalogController {

    @Autowired
    private CatalogRepository catalogRepository;

    @GetMapping("/{genre}")
    public CatalogResponse getCatalogByGenre(@PathVariable String genre) {
        List<Movie> movies = catalogRepository.findMoviesByGenre(genre);
        List<Serie> series = catalogRepository.findSeriesByGenre(genre);
        return new CatalogResponse(genre, movies, series);
    }
}
