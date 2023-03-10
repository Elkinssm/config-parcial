package com.dh.catalogservice.api.client;

import com.dh.catalogservice.domain.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "movie-service-parcial")
public interface IMovieServiceClient {
    @GetMapping("/movies/{genre}")
    List<Movie> getMoviesByCatalog(@PathVariable String genre);

    @PostMapping("/movies/save")
    Movie saveMovieByCatalog(@RequestBody Movie movie);
}
