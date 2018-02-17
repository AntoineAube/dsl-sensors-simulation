package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.TimeDependantSimulation
import groovy.time.BaseDuration

class TimeDependantSimulationScope extends SimulationScope {

    TimeDependantSimulationScope(TimeDependantSimulation simulation) {
        super(simulation)
    }

    def period(BaseDuration duration) {
        ((TimeDependantSimulation) simulation).loopPeriod = duration.toMilliseconds()
    }
}
