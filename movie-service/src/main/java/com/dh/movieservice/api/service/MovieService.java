package com.dh.movieservice.api.service;

import com.dh.movieservice.api.queue.MovieSender;
import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    private final MovieSender sender;

    public MovieService(MovieRepository repository, MovieSender sender) {
        this.repository = repository;
        this.sender = sender;
    }

    public List<Movie> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public void createMovie(Movie movie) {
        sender.sendMovie(movie);
        repository.save(movie);
    }
}
