package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;
import fr.polytech.dsl.dsl.model.structures.simulations.UnknownSimulation;

public class UnknownLaw extends Law {

    @Override
    public Simulation createBlankSimulation() {
        return new UnknownSimulation(this);
    }
}
