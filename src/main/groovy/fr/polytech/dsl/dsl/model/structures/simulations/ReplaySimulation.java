package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;

public class ReplaySimulation extends Simulation<ReplayLaw> {

    public ReplaySimulation(ReplayLaw associatedLaw) {
        super(associatedLaw);
    }
}
