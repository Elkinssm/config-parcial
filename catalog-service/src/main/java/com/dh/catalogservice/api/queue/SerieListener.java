package com.dh.catalogservice.api.queue;

import com.dh.catalogservice.api.repository.SerieRepository;
import com.dh.catalogservice.domain.model.Serie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SerieListener {
    private final SerieRepository repositorySerie;
    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(SerieListener.class);

    public SerieListener(RabbitTemplate rabbitTemplate, SerieRepository repositorySerie) {
        this.rabbitTemplate = rabbitTemplate;
        this.repositorySerie = repositorySerie;
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receiveSerie(@Payload Serie serie) {
        try {
            logger.info("Leyendo cola");
            repositorySerie.save(serie);
        } catch (Exception e) {
            logger.error("Error al crear la serie: {}", e.getMessage());
            rabbitTemplate.convertAndSend("error.exchange", "error.routingKey", serie);
        }
    }
}
