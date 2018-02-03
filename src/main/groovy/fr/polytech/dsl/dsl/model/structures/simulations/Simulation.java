package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.structures.laws.Law;

public abstract class Simulation<L extends Law> implements VisitableModel {

    private String sensorName;
    private final L associatedLaw;

    public Simulation(L associatedLaw) {
        this.associatedLaw = associatedLaw;
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
}
