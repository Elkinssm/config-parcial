package com.dh.serie.api.controller;

import com.dh.serie.model.Serie;
import com.dh.serie.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    @GetMapping
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        serieService.createSerie(serie);
        return serie.getId();
    }

    @PostMapping("/save/local")
    public ResponseEntity<?> saveSerie(@RequestBody Serie serie) {
        serieService.save(serie);
        return ResponseEntity.ok("Serie creada exitosamente");
    }


}
