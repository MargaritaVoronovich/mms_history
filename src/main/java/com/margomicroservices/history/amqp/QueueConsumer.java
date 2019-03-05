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

    private final ObjectMapper objectMapper;

    private final OrderAuditEventRepository orderAuditEventRepository;

    @Autowired
    public QueueConsumer(ObjectMapper objectMapper, OrderAuditEventRepository orderAuditEventRepository) {
        this.objectMapper = objectMapper;
        this.orderAuditEventRepository = orderAuditEventRepository;
    }

    @RabbitListener(queues = {"history_queue"})
    public void receiveMessage(String message) throws IOException {
        logger.info("Received (String) " + message);

        Long orderId = objectMapper.readTree(message).path("orderId").longValue();
        String status = objectMapper.readTree(message).path("status").asText();

        OrderAuditEvent orderAuditEvent = new OrderAuditEvent(orderId, status);

        orderAuditEventRepository.save(orderAuditEvent);
    }
}
