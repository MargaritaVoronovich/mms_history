create table order_audit_events
(
  id           BIGSERIAL NOT NULL CONSTRAINT orders_pkey PRIMARY KEY,
  order_id     BIGINT,
  order_status VARCHAR(20)
)