package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.laws.InterpolateLaw;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.InterpolateSimulationScope;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.SimulationScope;

public class InterpolateSimulation extends TimeDependantSimulation<InterpolateLaw> {

    public InterpolateSimulation(InterpolateLaw associatedLaw) {
        super(associatedLaw);
    }

    @Override
    public SimulationScope createSimulationScope() {
        return new InterpolateSimulationScope(this);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
