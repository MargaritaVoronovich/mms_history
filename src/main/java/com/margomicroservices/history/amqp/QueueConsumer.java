package com.margomicroservices.history.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.margomicroservices.history.model.OrderAuditEvent;
import com.margomicroservices.history.repository.OrderAuditEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderAuditEventRepository orderAuditEventRepository;

    @RabbitListener(queues = {"history_queue"})
    public void receiveMessage(String message) throws IOException, InterruptedException {
        logger.info("Received (String) " + message);

        //TODO: refactor
        Long orderId = objectMapper.readTree(message).path("order").path("id").longValue();

        OrderAuditEvent orderAuditEvent = new OrderAuditEvent(orderId, "created");

        orderAuditEventRepository.save(orderAuditEvent);
    }
}
