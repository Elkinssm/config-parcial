package com.dh.catalogservice.api.controller;

//import com.dh.catalogservice.api.service.CatalogService;

import com.dh.catalogservice.api.client.IMovieServiceClient;
import com.dh.catalogservice.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getCatalog(@PathVariable String genre) {
        return iMovieServiceClient.getMoviesByCatalog(genre);
    }
}
