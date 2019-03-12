package com.margomicroservices.history.containerinitializer.postgres;

public class PostgresOrderInitializer extends AbstractPostgresInitializer {
    public PostgresConfig config() {
        return new PostgresConfig(
                5432,
                "mms_order",
                "margaritavoronovich",
                "margaritavoronovich"
        );
    }
}
