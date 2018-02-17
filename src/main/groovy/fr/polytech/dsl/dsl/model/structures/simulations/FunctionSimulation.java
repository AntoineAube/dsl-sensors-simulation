package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.laws.FunctionLaw;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.FunctionSimulationScope;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.SimulationScope;

public class FunctionSimulation extends TimeDependantSimulation<FunctionLaw> {

    public FunctionSimulation(FunctionLaw associatedLaw) {
        super(associatedLaw);
    }

    @Override
    public SimulationScope createSimulationScope() {
        return new FunctionSimulationScope(this);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
