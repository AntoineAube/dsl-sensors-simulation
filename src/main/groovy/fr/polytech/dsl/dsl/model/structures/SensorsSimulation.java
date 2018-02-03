package fr.polytech.dsl.dsl.model.structures;

public class SensorsSimulation {

    private final KnownLawsRegister laws;
    private final SimulationDescription simulation;

    public SensorsSimulation() {
        laws = new KnownLawsRegister();
        simulation = new SimulationDescription();
    }

    public KnownLawsRegister getLaws() {
        return laws;
    }

    public SimulationDescription getSimulation() {
        return simulation;
    }
}
