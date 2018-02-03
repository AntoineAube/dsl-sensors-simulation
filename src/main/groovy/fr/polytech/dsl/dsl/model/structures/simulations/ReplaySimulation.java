package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;

public class ReplaySimulation extends Simulation<ReplayLaw> {

    public ReplaySimulation(ReplayLaw associatedLaw) {
        super(associatedLaw);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
