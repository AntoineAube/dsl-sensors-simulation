package fr.polytech.dsl.dsl

import fr.polytech.dsl.dsl.model.structures.SensorsSimulation

class SensorSimulationBinding extends Binding {

    SensorsSimulation sensorsSimulation

    SensorSimulationBinding() {
        sensorsSimulation = new SensorsSimulation()
    }
}
