package com.dh.catalogservice.api.queue;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MovieListener {


    private final CatalogService service;
    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(MovieListener.class);

    public MovieListener(CatalogService service, RabbitTemplate rabbitTemplate) {
        this.service = service;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receive(@Payload Movie movie) {
        try {
//            Thread.sleep(1000);
            service.createMovie(movie);
        } catch (Exception e) {
            logger.error("Error al crear la pelicula: {}", e.getMessage());
            rabbitTemplate.convertAndSend("error.exchange", "error.routingKey", movie);
        }
    }
}
