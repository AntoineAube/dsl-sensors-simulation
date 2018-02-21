package fr.polytech.dsl.dsl.execution;

import fr.polytech.dsl.dsl.execution.executors.*;
import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.Lot;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;
import fr.polytech.dsl.dsl.model.structures.SimulationContent;
import fr.polytech.dsl.dsl.model.structures.dashboards.Dashboard;
import fr.polytech.dsl.dsl.model.structures.dashboards.Panel;
import fr.polytech.dsl.dsl.model.structures.laws.*;
import fr.polytech.dsl.dsl.model.structures.simulations.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static fr.polytech.dsl.main.Main.GRAFANA_API_KEY;

public class SensorsSimulationExecutor implements ModelVisitor {

    private Lot currentLot;
    private int currentSimulationNumber;

    private List<Executor> executors;

    public SensorsSimulationExecutor(){
        executors = new ArrayList<>();
    }

    @Override
    public void visit(SensorsSimulation sensorsSimulation) {
        sensorsSimulation.getSimulation().accept(this);
        sensorsSimulation.getDashboards().forEach(dashboard -> dashboard.accept(this));
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
    public void visit(InterpolateSimulation interpolateSimulation) {
        for (int i = 0; i < currentSimulationNumber; i++) {
            InterpolationExecutor exec = new InterpolationExecutor(
                    currentLot.getName() + ":" + interpolateSimulation.getSensorName() + ":" + i,
                    interpolateSimulation.getDateFrom(),
                    interpolateSimulation.getDuration(),
                    interpolateSimulation.getNoise(),
                    1000.0f / interpolateSimulation.getSamplingFrequency().getFrequency(),
                    interpolateSimulation.getAssociatedLaw().getMinimumTime(),
                    interpolateSimulation.getAssociatedLaw().getMaximumTime(),
                    interpolateSimulation.getLoopPeriod(),
                    interpolateSimulation.getAssociatedLaw().getInterpolatedPoints()
            );
            executors.add(exec);
        }
    }

    @Override
    public void visit(FunctionSimulation functionSimulation) {
        for (int i = 0; i < currentSimulationNumber; i++) {
            FunctionExecutor exec = new FunctionExecutor(
                    currentLot.getName() + ":" + functionSimulation.getSensorName() + ":" + i,
                    functionSimulation.getDateFrom(),
                    functionSimulation.getDuration(),
                    functionSimulation.getNoise(),
                    1000.0f / functionSimulation.getSamplingFrequency().getFrequency(),
                    functionSimulation.getAssociatedLaw().getMinimumTime(),
                    functionSimulation.getAssociatedLaw().getMaximumTime(),
                    functionSimulation.getLoopPeriod(),
                    functionSimulation.getAssociatedLaw().getFunctionFragments(),
                    functionSimulation.getAssociatedLaw().getOtherwiseFragment()
            );
            executors.add(exec);
        }
    }

    @Override
    public void visit(UnknownSimulation unknownSimulation) {
        // nothing to do here
    }

    @Override
    public void visit(Dashboard dashboard) {
        DashboardSerializer serializer = new DashboardSerializer("127.0.0.1:3000", GRAFANA_API_KEY);
        try {
            serializer.saveDashboard(dashboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Panel panel) {
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
    public void visit(InterpolateLaw interpolateLaw) {
        // nothing to do here
    }

    @Override
    public void visit(FunctionLaw functionLaw) {
        // nothing to do here
    }

    @Override
    public void visit(UnknownLaw unknownLaw) {
        // nothing to do here
    }

    public void sendMeasures(DatabaseConfiguration configuration) {

        MeasureSerializer serializer = new MeasureSerializer(configuration);

        for (Executor exec : executors) {
//            System.out.print("|");
            while (!exec.hasFinished()){
                Measure measure = exec.getNext();
                if(measure != null) {
//                    System.out.print(".");
                    serializer.saveMeasure(measure);
                }
            }
            System.out.print("\n");
        }
    }
}
