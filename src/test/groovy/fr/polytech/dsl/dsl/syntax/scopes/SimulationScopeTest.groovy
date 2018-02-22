package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import groovy.time.TimeCategory

class SimulationScopeTest extends GroovyTestCase {

    private SensorSimulationBinding binding

    void setUp() {
        binding = new SensorSimulationBinding()
    }

    private void executeContent(Closure content) {
        content.delegate = new SimulationScope(binding)
        content.resolveStrategy = Closure.DELEGATE_FIRST

        use (TimeCategory) {
            content()
        }
    }

    void testNoLot() {
        executeContent { -> }

        assert binding.sensorsSimulation.simulation.lots.isEmpty()
    }

    void testWithOneLot() {
        executeContent { ->
            lot ('a') {

            }
        }

        assert binding.sensorsSimulation.simulation.lots.size() == 1
    }
}
