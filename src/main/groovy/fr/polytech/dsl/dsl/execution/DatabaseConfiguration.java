package fr.polytech.dsl.dsl.execution;

public class DatabaseConfiguration {

    private String databaseName;
    private String databaseLocation;
    private String grafanaAPIKey;

    public DatabaseConfiguration(String databaseName, String databaseLocation) {
        this.databaseName = databaseName;
        this.databaseLocation = databaseLocation;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseLocation() {
        return databaseLocation;
    }

    public void setDatabaseLocation(String databaseLocation) {
        this.databaseLocation = databaseLocation;
    }

    public String getGrafanaAPIKey() {
        return grafanaAPIKey;
    }

    public void setGrafanaAPIKey(String grafanaAPIKey) {
        this.grafanaAPIKey = grafanaAPIKey;
    }
}
