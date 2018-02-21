package fr.polytech.dsl.dsl.syntax.scopes.dashboards

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.dashboards.Panel

class PanelScope {

    private final Panel targetedPanel
    private final SensorSimulationBinding binding

    PanelScope(SensorSimulationBinding binding, Panel targetedPanel) {
        this.targetedPanel = targetedPanel
        this.binding = binding
    }

    def lot(String lotName) {
        targetedPanel.lot = binding.findLot(lotName).orElse(null)
    }

    def sensor(String sensorName) {
        targetedPanel.sensor = targetedPanel.lot.simulations.stream()
                .map({bundle -> bundle.simulation})
                .filter({simulation -> simulation.sensorName == sensorName})
                .findFirst()
                .orElse(null)
    }

    def number(int sensorNumber) {
        targetedPanel.sensorNumber = sensorNumber
    }
}
