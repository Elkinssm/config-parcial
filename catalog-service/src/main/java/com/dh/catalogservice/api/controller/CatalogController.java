package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.client.IMovieServiceClient;
import com.dh.catalogservice.api.client.ISerieServiceClient;
import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.CatalogResponse;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService service;
    @Autowired
    private IMovieServiceClient iMovieServiceClient;

    @Autowired
    private ISerieServiceClient iSerieServiceClient;


//    @GetMapping("/{genre}")
//    ResponseEntity<List<Movie>> getCatalogMovies(@PathVariable String genre) {
//        return ResponseEntity.ok().body(iMovieServiceClient.getMoviesByCatalog(genre));
//
//    }

    @GetMapping("/{genre}")
    public CatalogResponse getCatalogByGenre(@PathVariable String genre) {
        List<Movie> movies = iMovieServiceClient.getMoviesByCatalog(genre);
        List<Serie> series = iSerieServiceClient.getSerieByGenre(genre);
        return new CatalogResponse(genre, movies, series);
    }


//    @PostMapping("/save")
//    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
//        return ResponseEntity.ok().body(iMovieServiceClient.saveMovieByCatalog(movie));
//    }


    @PostMapping("/save-movie")
    ResponseEntity<String> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(service.createMovie(movie));
    }

    @PostMapping("/save-serie")
    ResponseEntity<String> saveSerie(@RequestBody Serie serie) {
        return ResponseEntity.ok().body(service.createSerie(serie));
    }


}
