package fr.polytech.dsl.dsl.syntax.scopes.simulations

import fr.polytech.dsl.dsl.model.structures.simulations.FunctionSimulation

class FunctionSimulationScope extends TimeDependantSimulationScope {

    FunctionSimulationScope(FunctionSimulation simulation) {
        super(simulation)
    }
}
