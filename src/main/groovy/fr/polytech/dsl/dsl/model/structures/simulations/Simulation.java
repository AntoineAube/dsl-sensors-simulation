package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.Model;
import fr.polytech.dsl.dsl.model.structures.laws.Law;

public abstract class Simulation<L extends Law> implements Model {

    private final L associatedLaw;

    public Simulation(L associatedLaw) {
        this.associatedLaw = associatedLaw;
    }

    public L getAssociatedLaw() {
        return associatedLaw;
    }
}
