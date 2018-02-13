package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;
import fr.polytech.dsl.dsl.model.structures.simulations.UnknownSimulation;

public class UnknownLaw extends Law {

    @Override
    public Class getValuesType() {
        return void.class;
    }

    @Override
    public Simulation createBlankSimulation() {
        return new UnknownSimulation(this);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
