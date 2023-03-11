package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.client.IMovieServiceClient;
import com.dh.catalogservice.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private IMovieServiceClient iMovieServiceClient;


    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getCatalog(@PathVariable String genre) {
        return ResponseEntity.ok().body(iMovieServiceClient.getMoviesByCatalog(genre));

    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(iMovieServiceClient.saveMovieByCatalog(movie));
    }

}
