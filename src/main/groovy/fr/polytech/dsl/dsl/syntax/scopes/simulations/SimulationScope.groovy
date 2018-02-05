package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.Simulation
import groovy.time.DatumDependentDuration
import groovy.time.TimeDuration

abstract class SimulationScope {

    private final Simulation simulation

    SimulationScope(Simulation simulation) {
        this.simulation = simulation
    }

    protected Simulation getSimulation() {
        return simulation
    }

    def offset(TimeDuration dateOffset) {
        simulation.dateOffset = dateOffset.toMilliseconds()
    }

    def offset(DatumDependentDuration dateOffset) {
        simulation.dateOffset = dateOffset.toMilliseconds()
    }
}
