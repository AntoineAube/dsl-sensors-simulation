package fr.polytech.dsl.dsl.syntax

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.syntax.scopes.LawsScope
import fr.polytech.dsl.dsl.syntax.scopes.SimulationScope

abstract class SensorSimulationScript extends Script {

    def laws(Closure lawsDescriptions) {
        lawsDescriptions.delegate = new LawsScope((SensorSimulationBinding) getBinding())
        lawsDescriptions.resolveStrategy = Closure.DELEGATE_FIRST

        lawsDescriptions()
    }

    def simulation(Closure simulationContent) {
        simulationContent.delegate = new SimulationScope((SensorSimulationBinding) getBinding())
        simulationContent.resolveStrategy = Closure.DELEGATE_FIRST

        simulationContent()
    }
}
