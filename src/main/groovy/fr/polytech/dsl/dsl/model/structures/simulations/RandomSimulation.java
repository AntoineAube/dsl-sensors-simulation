package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw;

public class RandomSimulation extends Simulation<RandomLaw> {

    public RandomSimulation(RandomLaw associatedLaw) {
        super(associatedLaw);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
