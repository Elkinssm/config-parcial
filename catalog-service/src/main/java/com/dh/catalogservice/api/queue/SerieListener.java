package com.dh.catalogservice.api.queue;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.Serie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SerieListener {


    private final CatalogService service;
    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(SerieListener.class);

    public SerieListener(CatalogService service, RabbitTemplate rabbitTemplate) {
        this.service = service;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie) {
        try {
            Thread.sleep(1000);
            service.create(serie);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Error al crear la serie: {}", e.getMessage());
            rabbitTemplate.convertAndSend("error.exchange", "error.routingKey", serie);
        }
    }
}
