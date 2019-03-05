package com.margomicroservices.history.service;

import com.margomicroservices.history.model.OrderAuditEvent;
import com.margomicroservices.history.repository.OrderAuditEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAuditEventService {
    private final OrderAuditEventRepository orderAuditEventRepository;

    @Autowired
    public OrderAuditEventService(OrderAuditEventRepository orderAuditEventRepository) {
        this.orderAuditEventRepository = orderAuditEventRepository;
    }

    public List<OrderAuditEvent> getAll() {
        return orderAuditEventRepository.findAll();
    }
}
