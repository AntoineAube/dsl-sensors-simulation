package fr.polytech.dsl.dsl

import fr.polytech.dsl.dsl.model.structures.SensorsSimulation
import fr.polytech.dsl.dsl.model.structures.laws.Law

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
}
