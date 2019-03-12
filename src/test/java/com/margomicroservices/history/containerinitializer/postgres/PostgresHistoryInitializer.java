package com.margomicroservices.history.containerinitializer.postgres;

public class PostgresHistoryInitializer extends AbstractPostgresInitializer {
    public PostgresConfig config() {
        return new PostgresConfig(
                5434,
                "mms_history",
                "margaritavoronovich",
                "margaritavoronovich"
        );
    }
}
