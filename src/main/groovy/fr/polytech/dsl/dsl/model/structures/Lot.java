package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

import java.util.ArrayList;
import java.util.List;

public class Lot {

    private final String name;
    private List<SimulationsBundle> simulations;

    public Lot(String name) {
        this.name = name;
        simulations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSimulations(Simulation simulation, int simulationCount) {
        simulations.add(new SimulationsBundle(simulation, simulationCount));
    }

    public List<SimulationsBundle> getSimulations() {
        return simulations;
    }

    private class SimulationsBundle {

        private final Simulation simulation;
        private final int simulationsCount;

        private SimulationsBundle(Simulation simulation, int simulationsCount) {
            this.simulation = simulation;
            this.simulationsCount = simulationsCount;
        }

        public Simulation getSimulation() {
            return simulation;
        }

        public int getSimulationsCount() {
            return simulationsCount;
        }
    }
}
