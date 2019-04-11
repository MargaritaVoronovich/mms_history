package com.margomicroservices.history.containerinitializer.microservice;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class OrderServiceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final int PORT = 8081;

    private GenericContainer SERVICE;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        SERVICE = new GenericContainer("mms_order")
                .waitingFor(Wait.forListeningPort())
                .withExposedPorts(PORT);

        SERVICE.start();

        applyProperties(applicationContext);
    }

    private void applyProperties(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "order.service.port:" + SERVICE.getMappedPort(PORT)
        ).applyTo(applicationContext);
    }
}
