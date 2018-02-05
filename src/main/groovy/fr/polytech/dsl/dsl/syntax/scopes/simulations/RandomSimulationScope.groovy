package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.RandomSimulation
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise

class RandomSimulationScope extends SimulationScope {

    RandomSimulationScope(RandomSimulation simulation) {
        super(simulation)
    }

    def noise(List<Integer> noiseValues) {
        ((RandomSimulation) simulation).noise = new Noise(noiseValues)
    }
}
