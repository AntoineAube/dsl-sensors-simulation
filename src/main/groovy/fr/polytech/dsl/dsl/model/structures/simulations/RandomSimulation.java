package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.RandomSimulationScope;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.SimulationScope;

import java.util.Collections;

public class RandomSimulation extends Simulation<RandomLaw> {

    public RandomSimulation(RandomLaw associatedLaw) {
        super(associatedLaw);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public SimulationScope createSimulationScope() {
        return new RandomSimulationScope(this);
    }
}
