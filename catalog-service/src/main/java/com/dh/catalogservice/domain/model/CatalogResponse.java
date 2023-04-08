package com.dh.catalogservice.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CatalogResponse {
    private String genre;
    private List<Movie> movies;
    private List<Serie> series;

    public CatalogResponse(String genre, List<Movie> movies, List<Serie> series) {
        this.genre = genre;
        this.movies = movies;
        this.series = series;
    }


}
