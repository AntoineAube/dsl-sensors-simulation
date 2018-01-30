package fr.polytech.dsl.dsl.execution;

import fr.polytech.dsl.dsl.execution.replays.ReplayReader;
import fr.polytech.dsl.dsl.execution.replays.ReplayReaderFactory;
import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.Configuration;
import fr.polytech.dsl.dsl.model.structures.Replay;
import fr.polytech.dsl.dsl.model.structures.Sensor;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SensorsSimulationExecutor implements ModelVisitor {

    private static final ReplayReaderFactory REPLAY_READER_FACTORY = new ReplayReaderFactory();

    private Configuration databaseConfiguration;
    private final List<Measure> measures;

    public SensorsSimulationExecutor() {
        databaseConfiguration = new Configuration();
        measures = new ArrayList<>();
    }

    @Override
    public void visit(Configuration configuration) {
        databaseConfiguration = configuration;
    }

    @Override
    public void visit(SensorsSimulation sensorsSimulation) {
        sensorsSimulation.getConfiguration().accept(this);

        sensorsSimulation.getReplays().forEach(this::visit);

        sensorsSimulation.getSensors().forEach(this::visit);
    }

    @Override
    public void visit(Replay replay) {
        ReplayReader<?> replayReader = REPLAY_READER_FACTORY.createReplayReader(replay);

        try {
            List<Measure> newMeasures = replayReader.readReplay();

            measures.addAll(newMeasures);
        } catch (IOException e) {
            throw new RuntimeException("Faulty replay during execution: " + replay);
        }
    }

    @Override
    public void visit(Sensor sensor) {
        // TODO Implement this.
    }

    public void sendMeasures() {
        MeasureSerializer serializer = new MeasureSerializer(databaseConfiguration);

        if (!measures.isEmpty()) {
            serializer.saveMeasures(measures);
        }
    }
}
