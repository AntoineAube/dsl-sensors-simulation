package fr.polytech.dsl.dsl.execution;

public class DatabaseConfiguration {

    private String databaseName;
    private String databaseLocation;

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
}
