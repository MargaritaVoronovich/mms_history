package com.margomicroservices.history.containerinitializer.postgres;

public class PostgresDeliveryInitializer extends AbstractPostgresInitializer {
    public PostgresConfig config() {
        return new PostgresConfig(
                5433,
                "mms_delivery",
                "margaritavoronovich",
                "margaritavoronovich"
        );
    }
}
