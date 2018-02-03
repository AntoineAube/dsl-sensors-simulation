package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

import java.util.ArrayList;
import java.util.List;

public class Lot implements VisitableModel {

    private final String name;
    private List<SimulationsBundle> simulations;

    public Lot(String name) {
        this.name = name;
        simulations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSimulations(Simulation simulation, int simulationsNumber) {
        simulations.add(new SimulationsBundle(simulation, simulationsNumber));
    }

    public List<SimulationsBundle> getSimulations() {
        return simulations;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    public class SimulationsBundle {

        private final Simulation simulation;
        private final int simulationsNumber;

        private SimulationsBundle(Simulation simulation, int simulationsNumber) {
            this.simulation = simulation;
            this.simulationsNumber = simulationsNumber;
        }

        public Simulation getSimulation() {
            return simulation;
        }

        public int getSimulationsNumber() {
            return simulationsNumber;
        }
    }
}
