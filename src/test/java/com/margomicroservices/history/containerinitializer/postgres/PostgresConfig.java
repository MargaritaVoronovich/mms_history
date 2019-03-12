package com.margomicroservices.history.containerinitializer.postgres;

public class PostgresConfig {
    private int port;
    private String db;
    private String user;
    private String password;

    PostgresConfig(int port, String db, String user, String password) {
        this.port = port;
        this.db = db;
        this.user = user;
        this.password = password;
    }

    int getPort() {
        return port;
    }

    String getDb() {
        return db;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostgresConfig that = (PostgresConfig) o;

        if (port != that.port) return false;
        if (!db.equals(that.db)) return false;
        if (!user.equals(that.user)) return false;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = port;
        result = 31 * result + db.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
