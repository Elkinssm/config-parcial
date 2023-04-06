package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.repository.MovieRepository;
import com.dh.catalogservice.api.repository.SerieRepository;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final SerieRepository serieRepository;
    private final MovieRepository movieRepository;


    public CatalogService(SerieRepository repository, MovieRepository movieRepository) {
        this.serieRepository = repository;
        this.movieRepository = movieRepository;
    }

    public List<Serie> getAll() {
        return serieRepository.findAll();
    }

    public List<Serie> getSerieBygGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
    }

    public List<Movie> getMoviesBygGenre(String genre) {
        return movieRepository.findAllByGenre(genre);
    }

    public String createSerie(Serie serie) {
        serieRepository.save(serie);
        return serie.getId();
    }

    public String createMovie(Movie movie) {
        movieRepository.save(movie);
        return movie.getId();
    }
}
