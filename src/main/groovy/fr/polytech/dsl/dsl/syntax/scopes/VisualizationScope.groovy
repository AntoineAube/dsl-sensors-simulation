package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.dashboards.Dashboard
import fr.polytech.dsl.dsl.syntax.scopes.dashboards.DashboardScope

class VisualizationScope {

    private final SensorSimulationBinding binding

    VisualizationScope(SensorSimulationBinding binding) {
        this.binding = binding
    }

    def dashboard(String dashboardName, Closure dashboardDescription) {
        Dashboard addedDashboard = new Dashboard()
        addedDashboard.title = dashboardName

        dashboardDescription.delegate = new DashboardScope(binding, addedDashboard)
        dashboardDescription.resolveStrategy = Closure.DELEGATE_FIRST

        dashboardDescription()

        binding.sensorsSimulation.dashboards.add(addedDashboard)
    }
}
