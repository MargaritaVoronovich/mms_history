package com.margomicroservices.history.containerinitializer.postgres;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

abstract public class AbstractPostgresInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, IPostgresConfigurable {
    private GenericContainer POSTGRES;

    private String getHost() {
        return POSTGRES.getContainerIpAddress();
    }

    private int getPort() {
        return POSTGRES.getMappedPort(config().getPort());
    }

    private String getUrl() {
        return "jdbc:postgresql:" + UriComponentsBuilder.newInstance()
                .host(getHost()).port(getPort()).path(config().getDb())
                .toUriString();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        POSTGRES = new GenericContainer("postgres:10.4-alpine")
                .withEnv("POSTGRES_DB", config().getDb())
                .withEnv("POSTGRES_USER", config().getUser())
                .withEnv("POSTGRES_PASSWORD", config().getPassword())
                .waitingFor(Wait.forListeningPort())
                .withExposedPorts(config().getPort());

        POSTGRES.start();

        applyProperties(applicationContext);
    }

    private void applyProperties(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "spring.datasource.url:" + getUrl(),
                "spring.datasource.username:" + config().getUser(),
                "spring.datasource.password:" + config().getPassword()
        ).applyTo(applicationContext);
    }
}
