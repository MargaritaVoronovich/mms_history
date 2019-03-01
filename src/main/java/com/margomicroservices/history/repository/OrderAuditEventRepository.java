package com.margomicroservices.history.repository;


import com.margomicroservices.history.model.OrderAuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAuditEventRepository extends JpaRepository<OrderAuditEvent, Long> {
}
