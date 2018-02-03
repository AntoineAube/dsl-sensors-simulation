package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.Lot
import fr.polytech.dsl.dsl.model.structures.laws.Law
import fr.polytech.dsl.dsl.model.structures.laws.UnknownLaw
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation

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

                if (associatedLaw.isPresent()) {
                    Simulation simulation = associatedLaw.get().createBlankSimulation()
                    simulation.sensorName = sensorName

                    lot.addSimulations(simulation, sensorsNumber)
                } else {
                    Law unknown = new UnknownLaw()
                    unknown.name = lawName

                    lot.addSimulations(unknown.createBlankSimulation(), sensorsNumber)
                }
            }]
        }]
    }
}
