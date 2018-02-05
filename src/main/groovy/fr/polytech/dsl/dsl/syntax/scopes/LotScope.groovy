package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.Lot
import fr.polytech.dsl.dsl.model.structures.laws.Law
import fr.polytech.dsl.dsl.model.structures.laws.UnknownLaw
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation
import fr.polytech.dsl.dsl.model.structures.simulations.UnknownSimulation

class LotScope {

    private final SensorSimulationBinding binding
    private final Lot lot

    LotScope(SensorSimulationBinding binding, Lot lot) {
        this.binding = binding
        this.lot = lot
    }

    def contains(int sensorsNumber) {
        [sensors: {String sensorName ->
            [following: {String lawName ->
                Optional<Law> associatedLaw = binding.findLaw(lawName)

                Law unknown = new UnknownLaw()
                unknown.name = lawName
                Simulation simulation = unknown.createBlankSimulation()

                if (associatedLaw.isPresent()) {
                    simulation = associatedLaw.get().createBlankSimulation()
                    simulation.sensorName = sensorName

                    lot.addSimulations(simulation, sensorsNumber)
                } else {
                    lot.addSimulations(unknown.createBlankSimulation(), sensorsNumber)
                }

                [modifiedBy: {Closure simulationModifiers ->
                    simulationModifiers.delegate = simulation.createSimulationScope()
                    simulationModifiers.resolveStrategy = DELEGATE_FIRST

                    simulationModifiers()
                }]
            }]
        }]
    }
}
