package com.margomicroservices.history.controller;

import com.margomicroservices.history.model.OrderAuditEvent;
import com.margomicroservices.history.service.OrderAuditEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderAuditEventController {

    @Autowired
    private OrderAuditEventService orderAuditEventService;

    @GetMapping("/deliveries")
    public ResponseEntity<List<OrderAuditEvent>> all() {
        return ResponseEntity.ok(orderAuditEventService.getAll());
    }
}
