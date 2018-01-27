package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.ModelVisitor;

public class Configuration implements VisitableModel {

    private static final String DEFAULT_DATABASE_LOCATION = "http://localhost:8086";
    private static final String DEFAULT_DATABASE_NAME = "sensors_simulation";

    private String databaseName;
    private String databaseLocation;

    public Configuration() {
        databaseName = DEFAULT_DATABASE_NAME;
        databaseLocation = DEFAULT_DATABASE_LOCATION;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseLocation() {
        return databaseLocation;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setDatabaseLocation(String databaseLocation) {
        this.databaseLocation = databaseLocation;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
