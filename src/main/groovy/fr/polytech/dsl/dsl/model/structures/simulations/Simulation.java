package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.structures.laws.Law;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.SimulationScope;

public abstract class Simulation<L extends Law> implements VisitableModel {

    private String sensorName;
    private final L associatedLaw;
    private long dateOffset;

    public Simulation(L associatedLaw) {
        this.associatedLaw = associatedLaw;
        dateOffset = 0;
    }

    public L getAssociatedLaw() {
        return associatedLaw;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public long getDateOffset() {
        return dateOffset;
    }

    public void setDateOffset(long dateOffset) {
        this.dateOffset = dateOffset;
    }

    public abstract SimulationScope createSimulationScope();
}
