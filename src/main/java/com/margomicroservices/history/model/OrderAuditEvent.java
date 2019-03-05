package com.margomicroservices.history.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_audit_events")
@Data
@NoArgsConstructor
public class OrderAuditEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "bigint")
    private Long orderId;

    @Column(columnDefinition = "varchar(20)")
    private OrderStatus orderStatus;

    public OrderAuditEvent(Long orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public enum OrderStatus {
        CREATED, DELIVERED;
    }
}
