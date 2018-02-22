package fr.polytech.dsl.dsl.syntax.scopes.dashboards

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.dashboards.Dashboard

class DashboardScopeTest extends GroovyTestCase {

    private SensorSimulationBinding binding
    private Dashboard dashboard

    void setUp() {
        binding = new SensorSimulationBinding()
        dashboard = new Dashboard()
    }

    void testOneDashboard() {
        def content = { ->
            from "25/04/2017 15:00"
            to "25/04/2017 15:01"

            graph ("b") {
                lot 'l'
                sensor 's'
                number 1
            }
        }

        content.delegate = new DashboardScope(binding, dashboard)
        content.resolveStrategy = Closure.DELEGATE_FIRST

        content()
    }
}
