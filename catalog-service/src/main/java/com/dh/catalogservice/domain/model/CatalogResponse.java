package com.dh.catalogservice.domain.model;

import java.util.List;

public class CatalogResponse {
    private String genre;
    private List<Movie> movies;
    private List<Serie> series;

    public CatalogResponse(String genre, List<Movie> movies, List<Serie> series) {
        this.genre = genre;
        this.movies = movies;
        this.series = series;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
}
