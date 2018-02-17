package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.InterpolateSimulation

class InterpolateSimulationScope extends TimeDependantSimulationScope {

    InterpolateSimulationScope(InterpolateSimulation simulation) {
        super(simulation)
    }
}
