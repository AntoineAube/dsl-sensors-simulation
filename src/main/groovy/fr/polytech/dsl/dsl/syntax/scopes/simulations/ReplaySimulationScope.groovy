package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.ReplaySimulation
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise

class ReplaySimulationScope extends SimulationScope {

    ReplaySimulationScope(ReplaySimulation simulation) {
        super(simulation)
    }

    def noise(List<Integer> noiseValues) {
        ((ReplaySimulation) simulation).noise = new Noise(noiseValues)
    }
}
