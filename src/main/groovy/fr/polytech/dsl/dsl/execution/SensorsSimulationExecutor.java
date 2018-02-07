package fr.polytech.dsl.dsl.execution;

import fr.polytech.dsl.dsl.execution.replays.ReplayReaderFactory;

public class SensorsSimulationExecutor {

    private static final ReplayReaderFactory REPLAY_READER_FACTORY = new ReplayReaderFactory();

    /*private DatabaseConfiguration databaseConfiguration;
    private final List<Measure> measures;

    public SensorsSimulationExecutor() {
        databaseConfiguration = new DatabaseConfiguration();
        measures = new ArrayList<>();
    }

    @Override
    public void visit(DatabaseConfiguration configuration) {
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
    }*/
}
