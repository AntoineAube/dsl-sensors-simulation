package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.Lot

class SimulationScope {

    private final SensorSimulationBinding binding

    SimulationScope(SensorSimulationBinding binding) {
        this.binding = binding
    }

    def lot(String lotName, Closure lotContent) {
        Lot newLot = new Lot(lotName)

        lotContent.delegate = new LotScope(binding, newLot)
        lotContent.resolveStrategy = Closure.DELEGATE_FIRST

        lotContent()

        binding.sensorsSimulation.simulation.lots.add(newLot)
    }
}
