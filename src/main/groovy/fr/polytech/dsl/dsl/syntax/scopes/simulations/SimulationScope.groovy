package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.Simulation
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.SamplingFrequency
import groovy.time.BaseDuration

import java.text.SimpleDateFormat

abstract class SimulationScope {

    private final Simulation simulation

    SimulationScope(Simulation simulation) {
        this.simulation = simulation
    }

    protected Simulation getSimulation() {
        return simulation
    }

    def from(String string){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm")

        Date fromDate = format.parse(string)

        simulation.dateFrom = fromDate.getTime()
    }

    def noise(List<Integer> noiseValues) {
        simulation.noise = new Noise(noiseValues)
    }

    def during(BaseDuration duration) {
        simulation.duration = duration.toMilliseconds()

        [at: {SamplingFrequency frequency ->
            simulation.samplingFrequency = frequency
        },
         sampleEvery: {BaseDuration period ->
            simulation.samplingFrequency = new SamplingFrequency((double) 1000 / period.toMilliseconds())
        }]
    }

    def sampleEvery(BaseDuration period) {
        simulation.samplingFrequency = new SamplingFrequency((double) 1000 / period.toMilliseconds())
    }
}
