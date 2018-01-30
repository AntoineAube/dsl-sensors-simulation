package fr.polytech.dsl.dsl.validation;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.Configuration;
import fr.polytech.dsl.dsl.model.structures.Replay;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;

import java.util.Arrays;
import java.util.List;

public class SensorSimulationValidator implements ModelVisitor {

    private static final List<String> AUTHORIZED_REPLAY_FORMAT = Arrays.asList("csv", "json");

    @Override
    public void visit(Configuration configuration) {
        if (configuration.getDatabaseLocation() == null) {
            throw new InvalidModelException("Database location is null", configuration);
        }

        if (configuration.getDatabaseLocation().isEmpty()) {
            throw new InvalidModelException("Database location is empty", configuration);
        }

        if (configuration.getDatabaseName() == null) {
            throw new InvalidModelException("Database name is null", configuration);
        }

        if (configuration.getDatabaseName().isEmpty()) {
            throw new InvalidModelException("Database name is name", configuration);
        }
    }

    @Override
    public void visit(SensorsSimulation sensorsSimulation) {
        sensorsSimulation.getConfiguration().accept(this);

        sensorsSimulation.getReplays().forEach(this::visit);
    }

    @Override
    public void visit(Replay replay) {
        if (!AUTHORIZED_REPLAY_FORMAT.contains(replay.getSourceType())) {
            throw new InvalidModelException("Source type is not known", replay);
        }

        if (!replay.getSourceLocation().exists()) {
            throw new InvalidModelException("Source file does not exist", replay);
        }

        if (replay.getSourceLocation().isDirectory()) {
            throw new InvalidModelException("Source file is a directory", replay);
        }

        if (replay.getTimestampOffset() < 0) {
            throw new InvalidModelException("Offset is negative", replay);
        }

        Replay.LocationsSet locations = replay.getLocations();

        if (locations.getTimesLocation() == null) {
            throw new InvalidModelException("Times location is not given", replay);
        }

        if (locations.getValuesLocation() == null) {
            throw new InvalidModelException("Values location is not given", replay);
        }

        if (locations.getSensorsLocation() == null) {
            throw new InvalidModelException("Sensors location is not given", replay);
        }
    }
}
