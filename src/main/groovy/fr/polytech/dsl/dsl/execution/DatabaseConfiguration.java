package fr.polytech.dsl.dsl.execution;

public class DatabaseConfiguration {

    private final String databaseName;
    private final String databaseLocation;

    public DatabaseConfiguration(String databaseName, String databaseLocation) {
        this.databaseName = databaseName;
        this.databaseLocation = databaseLocation;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseLocation() {
        return databaseLocation;
    }
}
