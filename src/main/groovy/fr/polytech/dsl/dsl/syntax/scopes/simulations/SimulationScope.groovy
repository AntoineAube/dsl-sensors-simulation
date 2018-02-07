package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.Simulation
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.SamplingFrequency
import groovy.time.BaseDuration

abstract class SimulationScope {

    private final Simulation simulation

    SimulationScope(Simulation simulation) {
        this.simulation = simulation
    }

    protected Simulation getSimulation() {
        return simulation
    }

    def noise(List<Integer> noiseValues) {
        simulation.noise = new Noise(noiseValues)
    }

    def during(BaseDuration duration) {
        simulation.duration = duration.toMilliseconds()

        [at: {SamplingFrequency frequency ->
            simulation.samplingFrequency = frequency
        }]
    }
}
