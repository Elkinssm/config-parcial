package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.client.IMovieServiceClient;
import com.dh.catalogservice.api.client.ISerieServiceClient;
import com.dh.catalogservice.api.repository.MovieRepository;
import com.dh.catalogservice.api.repository.SerieRepository;
import com.dh.catalogservice.domain.model.CatalogResponse;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @Autowired
    private ISerieServiceClient iSerieServiceClient;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SerieRepository serieRepository;


    @GetMapping("/all/{genre}")
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

    @GetMapping("/all-movies")
    ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok().body(movieRepository.findAll());
    }

    @GetMapping("/all-movies/{genre}")
    public List<Movie> getMovieByGenreOff(@PathVariable String genre) {
        return movieRepository.findAllByGenre(genre);
    }

    /**
     * Este método devuelve la lista de películas por género.
     *
     * @param genre el género de la película
     * @return la lista de películas que corresponden al género especificado
     * @throws RuntimeException si no se puede obtener la lista de películas por ningún medio
     */
    @CircuitBreaker(name = "movies", fallbackMethod = "fallback")
    @GetMapping("/fall/{genre}")
    public List<Movie> getMovieByGenre(@PathVariable String genre) {
        List<Movie> movieList = iMovieServiceClient.getMoviesByCatalog(genre);
        if (movieList.isEmpty()) {
            movieList = movieRepository.findAllByGenre(genre);
        }
        return movieList;
    }

    /**
     * Este método es el fallback que se ejecuta si no se puede obtener la lista de películas
     * desde el servicio externo o desde la base de datos local.
     *
     * @param genre el género de la película
     * @param e la excepción que causó el fallback
     * @return la lista de películas que se obtiene desde la base de datos local
     * @throws RuntimeException si no se puede obtener ninguna película desde la base de datos local
     */
    private List<Movie> fallback(String genre, RuntimeException e) {
        System.out.println("Usando fallback de movies " + genre);
        List<Movie> movieList = movieRepository.findAllByGenre(genre);
        if (movieList.isEmpty()) {
            throw new RuntimeException("No se pudo encontrar ninguna película en la base de datos local");
        }
        return movieList;
    }

//    @CircuitBreaker(name = "movies", fallbackMethod = "fallback")
//    @GetMapping("/fall/{genre}")
//    public List<Movie> getMovieByGenre(@PathVariable String genre) {
//        throw new RuntimeException("Error al obtener películas del catálogo");
//    }
//
//    private List<Movie> fallback(String genre, RuntimeException e) {
//        System.out.println("Usando fallback de movies " + genre);
//        return movieRepository.findAllByGenre(genre);
//    }

//    @CircuitBreaker(name = "movies", fallbackMethod = "fallback")
//    @GetMapping("/fall/{genre}")
//    public List<Movie> getMovieByGenre(@PathVariable String genre) {
//        List<Movie> movieList = iMovieServiceClient.getMoviesByCatalog(genre);
//        return movieList;
//
//    }
//
//    private List<Movie> fallback(String genre, RuntimeException e) {
//        System.out.println("Usando fallback de movies " + genre);
//        return movieRepository.findAllByGenre(genre);
//    }


//    @CircuitBreaker(name="movies",fallbackMethod = "fallback")
//    @GetMapping("/{genre}")
//    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
//        return ResponseEntity.ok().body(iMovieServiceClient.getMoviesByCatalog(genre));
//    }
//
//    private ResponseEntity<List<Movie>> fallback(@PathVariable String genre, RuntimeException e) {
//        return new ResponseEntity("Base de datos no funciona", HttpStatus.OK);
//    }


}
