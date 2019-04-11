package com.margomicroservices.history.containerinitializer.microservice;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class DeliveryServiceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final int PORT = 8082;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final GenericContainer SERVICE = new GenericContainer("mms_delivery")
                .waitingFor(Wait.forListeningPort())
                .withExposedPorts(PORT);

        SERVICE.start();
    }

    //rabbitmq port in app properties inside container
    //postgres port
}
