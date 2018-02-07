package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.RandomSimulation
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise

class RandomSimulationScope extends SimulationScope {

    RandomSimulationScope(RandomSimulation simulation) {
        super(simulation)
    }
}
