package com.dh.catalogservice.api.client;

import com.dh.catalogservice.domain.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "serie-service-parcial")
public interface ISerieServiceClient {
    @GetMapping
    List<Serie> getAll();

    @GetMapping("/series/{genre}")
    List<Serie> getSerieByGenre(@PathVariable String genre);

    @PostMapping("/series/save")
    @ResponseStatus(HttpStatus.CREATED)
    String create(@RequestBody Serie serie);

}
