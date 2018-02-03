package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw;

public class RandomSimulation extends Simulation<RandomLaw> {

    public RandomSimulation(RandomLaw associatedLaw) {
        super(associatedLaw);
    }
}
