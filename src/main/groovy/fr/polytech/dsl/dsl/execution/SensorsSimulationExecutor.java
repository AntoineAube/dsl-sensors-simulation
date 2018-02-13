package fr.polytech.dsl.dsl.execution;

import fr.polytech.dsl.dsl.execution.executors.RandomExecutor;
import fr.polytech.dsl.dsl.execution.executors.ReplayExecutor;
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

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import fr.polytech.dsl.dsl.execution.executors.Executor;

public class SensorsSimulationExecutor implements ModelVisitor{

    private Lot currentLot;
    private int currentSimulationNumber;

    private List<Executor> executors;

    public SensorsSimulationExecutor(){
        executors = new ArrayList<>();
    }

    @Override
    public void visit(SensorsSimulation sensorsSimulation) {
        sensorsSimulation.getSimulation().accept(this);
    }

    @Override
    public void visit(SimulationContent simulationContent) {
        for(Lot lot : simulationContent.getLots()){
            lot.accept(this);
        }
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
    public void visit(RandomSimulation simulation) {
        for (int i = 0; i < currentSimulationNumber; i++) {
            RandomExecutor exec = new RandomExecutor(
                    currentLot.getName()+":"+simulation.getSensorName()+":"+i,
                    simulation.getDateFrom(),
                    simulation.getDuration(),
                    simulation.getNoise(),
                    1000.0f/simulation.getSamplingFrequency().getFrequency(),
                    simulation.getAssociatedLaw().getPossibleValues(),
                    simulation.getAssociatedLaw().getValuesType()
            );
            executors.add(exec);
        }
    }

    @Override
    public void visit(ReplaySimulation simulation) {
        try {
            for (int i = 0; i < currentSimulationNumber; i++) {
                ReplayExecutor exec = new ReplayExecutor(
                        currentLot.getName()+":"+simulation.getSensorName()+":"+i,
                        simulation.getDateOffset(),
                        simulation.getDuration(),
                        simulation.getNoise(),
                        1000.0f/simulation.getSamplingFrequency().getFrequency(),
                        simulation.getAssociatedLaw().getSourceFilePath(),
                        simulation.getAssociatedLaw().getIndexes(),
                        simulation.getAssociatedLaw().getTargetedSensor(),
                        simulation.getAssociatedLaw().getValuesType()
                );
                executors.add(exec);
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(UnknownSimulation unknownSimulation) {
        // nothing to do here
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

    public void sendMeasures(DatabaseConfiguration configuration) {

        MeasureSerializer serializer = new MeasureSerializer(configuration);

        for (Executor exec : executors) {
            System.out.print("|");
            while (!exec.hasFinished()){
                Measure measure = exec.getNext();
                if(measure != null) {
                    System.out.print(".");
                    serializer.saveMeasure(measure);
                }
            }
            System.out.print("\n");
        }
    }
}
