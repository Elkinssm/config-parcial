package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.client.IMovieServiceClient;
import com.dh.catalogservice.api.client.ISerieServiceClient;
import com.dh.catalogservice.api.repository.MovieRepository;
import com.dh.catalogservice.api.repository.SerieRepository;
import com.dh.catalogservice.domain.model.CatalogResponse;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private IMovieServiceClient iMovieServiceClient;

    @Autowired
    private ISerieServiceClient iSerieServiceClient;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SerieRepository serieRepository;


    @GetMapping("/{genre}")
    public CatalogResponse getCatalogByGenreOnline(@PathVariable String genre) {
        List<Movie> movies = iMovieServiceClient.getMoviesByCatalog(genre);
        List<Serie> series = iSerieServiceClient.getSerieByGenre(genre);
        return new CatalogResponse(genre, movies, series);
    }

    @GetMapping("/all")
    public CatalogResponse getCatalogByGenreOffline() {
        List<Movie> movies = movieRepository.findAll();
        List<Serie> series = serieRepository.findAll();
        return new CatalogResponse(" ", movies, series);
    }


}
