package fr.polytech.dsl.dsl

import fr.polytech.dsl.dsl.model.structures.Lot
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation
import fr.polytech.dsl.dsl.model.structures.laws.Law
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation

class SensorSimulationBinding extends Binding {

    SensorsSimulation sensorsSimulation

    SensorSimulationBinding() {
        sensorsSimulation = new SensorsSimulation()
    }

    Optional<Law> findLaw(String lawName) {
        List<Law> laws = sensorsSimulation.laws

        for (Law law : laws) {
            if (law.name == lawName) {
                return Optional.of(law)
            }
        }

        return Optional.empty()
    }

    Optional<Lot> findLot(String lotName) {
        List<Lot> lots = sensorsSimulation.simulation.lots

        for (Lot lot : lots) {
            if (lot.name == lotName) {
                return Optional.of(lot)
            }
        }

        return Optional.empty()
    }
}
