package fr.polytech.dsl.dsl.execution;

import fr.polytech.dsl.dsl.execution.executors.RandomExecutor;
import fr.polytech.dsl.dsl.execution.replays.ReplayReaderFactory;
import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.Lot;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;
import fr.polytech.dsl.dsl.model.structures.SimulationContent;
import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw;
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;
import fr.polytech.dsl.dsl.model.structures.laws.UnknownLaw;
import fr.polytech.dsl.dsl.model.structures.simulations.RandomSimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.ReplaySimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.UnknownSimulation;

import java.util.List;
import fr.polytech.dsl.dsl.execution.executors.Executor;

public class SensorsSimulationExecutor implements ModelVisitor{

    private Lot currentLot;
    private int currentSimulationNumber;

    private List<Executor> executors;

    @Override
    public void visit(SensorsSimulation sensorsSimulation) {

    }

    @Override
    public void visit(SimulationContent simulationContent) {

    }

    @Override
    public void visit(Lot lot) {
        currentLot = lot;
        
        for (Lot.SimulationsBundle bundle : lot.getSimulations()) {
            currentSimulationNumber = bundle.getSimulationsNumber();
            bundle.getSimulation().accept(this);

        }
    }

    @Override
    public void visit(RandomSimulation randomSimulation) {
        for (int i = 0; i < currentSimulationNumber; i++) {
            RandomExecutor exec = new RandomExecutor(
                randomSimulation.getNoise(),
                1.0f/randomSimulation.getSamplingFrequency().getFrequency(),
                randomSimulation.getDateFrom(),
                randomSimulation.getDuration(),
                currentLot.getName()+":"+randomSimulation.getSensorName()+":"+i,
                randomSimulation.getAssociatedLaw().getPossibleValues()
            );
            executors.add(exec);
        }
    }

    @Override
    public void visit(ReplaySimulation replaySimulation) {

    }

    @Override
    public void visit(UnknownSimulation unknownSimulation) {

    }

    @Override
    public void visit(RandomLaw randomLaw) {
        // nothing to do here
    }

    @Override
    public void visit(ReplayLaw replayLaw) {
        // nothing to do here
    }

    @Override
    public void visit(UnknownLaw unknownLaw) {
        // nothing to do here
    }
    // private static final ReplayReaderFactory REPLAY_READER_FACTORY = new ReplayReaderFactory();

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
