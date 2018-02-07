package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.ReplaySimulation
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise
import groovy.time.BaseDuration
import groovy.time.DatumDependentDuration
import groovy.time.TimeDuration

class ReplaySimulationScope extends SimulationScope {

    ReplaySimulationScope(ReplaySimulation simulation) {
        super(simulation)
    }

    def offset(BaseDuration dateOffset) {
        ((ReplaySimulation) simulation).dateOffset = dateOffset.toMilliseconds()
    }
}
