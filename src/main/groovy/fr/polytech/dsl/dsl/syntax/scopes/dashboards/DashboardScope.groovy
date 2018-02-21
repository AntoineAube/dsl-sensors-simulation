package fr.polytech.dsl.dsl.syntax.scopes.dashboards

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.dashboards.Panel
import fr.polytech.dsl.dsl.model.structures.dashboards.Panel.PanelType

import java.text.SimpleDateFormat

class DashboardScope {

    private final SensorSimulationBinding binding

    DashboardScope(SensorSimulationBinding binding) {
        this.binding = binding
    }

    private def makePanel(String panelName, PanelType chosenType, Closure panelParameters) {
        Panel addedPanel = new Panel(panelName)
        addedPanel.type = chosenType

        panelParameters.delegate = new PanelScope(binding, addedPanel)
        panelParameters.resolveStrategy = Closure.DELEGATE_FIRST

        panelParameters()

        binding.sensorsSimulation.dashboard.panels.add(addedPanel)
    }

    def graph(String panelName, Closure graphContent) {
        makePanel(panelName, PanelType.GRAPH, graphContent)
    }

    def table(String panelName, Closure tableContent) {
        makePanel(panelName, PanelType.TABLE, tableContent)
    }

    def from(String stringifiedDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm")

        Date fromDate = format.parse(stringifiedDate)

        binding.sensorsSimulation.dashboard.from = fromDate
    }

    def to(String stringifiedDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm")

        Date toDate = format.parse(stringifiedDate)

        binding.sensorsSimulation.dashboard.to = toDate
    }
}