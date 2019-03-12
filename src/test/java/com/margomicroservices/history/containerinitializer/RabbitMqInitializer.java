package com.margomicroservices.history.containerinitializer;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class RabbitMqInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final int PORT = 5672;
    private static final String RABBIT_USER = "admin";
    private static final String RABBIT_PASSWORD = "admin";

    private GenericContainer RABBIT_MQ;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        RABBIT_MQ = new GenericContainer("rabbitmq:3-management")
                .withClasspathResourceMapping(
                        "../order/conf/myrabbit.conf",
                        "/etc/rabbitmq/rabbitmq.conf",
                        BindMode.READ_ONLY
                )
                .waitingFor(Wait.forListeningPort())
                .withExposedPorts(PORT);

        RABBIT_MQ.start();

        applyProperties(applicationContext);
    }

    private void applyProperties(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "fanout.exchange:" + "orders.fanout",
                "spring.rabbitmq.host:" + RABBIT_MQ.getContainerIpAddress(),
                "spring.rabbitmq.port:" + RABBIT_MQ.getMappedPort(PORT),
                "spring.rabbitmq.username:" + RABBIT_USER,
                "spring.rabbitmq.password:" + RABBIT_PASSWORD
        ).applyTo(applicationContext);
    }
}
