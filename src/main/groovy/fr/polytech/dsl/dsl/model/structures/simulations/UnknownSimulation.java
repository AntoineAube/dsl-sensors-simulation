package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.laws.UnknownLaw;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.SimulationScope;

public class UnknownSimulation extends Simulation<UnknownLaw> {

    public UnknownSimulation(UnknownLaw associatedLaw) {
        super(associatedLaw);
    }

    @Override
    public SimulationScope createSimulationScope() {
        return new SimulationScope(this) { /* Nothing to implement there. */  };
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
