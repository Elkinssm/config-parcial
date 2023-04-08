package com.dh.serie.api.controller;

import com.dh.serie.model.Serie;
import com.dh.serie.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class RegisterSerieController {

    @Autowired
    private SerieService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveSerie(@RequestBody Serie serie) {
        service.createSerie(serie);
        return ResponseEntity.ok("Serie creada exitosamente");
    }
}
