package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding

class LawsScopeTest extends GroovyTestCase {

    private SensorSimulationBinding binding

    private void executeContent(Closure content) {
        content.delegate = new LawsScope(binding)
        content.resolveStrategy = Closure.DELEGATE_FIRST

        content()
    }

    void setUp() {
        binding = new SensorSimulationBinding()

        assert binding.sensorsSimulation.laws.isEmpty()
    }

    void testShouldFindNoLaw() {
        executeContent { -> }

        assert binding.sensorsSimulation.laws.isEmpty()
    }

    void testShouldAddOneFunctionLaw() {
        executeContent { ->
            law 'a' is function(0..3) {
                returning Integer

                otherwise {x}
            }
        }

        assert binding.sensorsSimulation.laws.size() == 1
    }

    void testShouldAddOneInterpolateLaw() {
        executeContent { ->
            law 'a' is interpolate(0..3) {
                x 0     y 1
                x 2     y 3
            }
        }

        assert binding.sensorsSimulation.laws.size() == 1
    }

    void testShouldAddTwoRandomLaw() {
        executeContent { ->
            law 'a' is random(1..5)
            law 'b' is random(true, false)
        }

        assert binding.sensorsSimulation.laws.size() == 2
    }

    void testShouldAddOneReplay() {
        executeContent { ->
            law 'a' is replay('a.csv') {
                fetch 's' whose values are Integer

                times in 1
                values in 2
                sensors in 3
            }
        }

        assert binding.sensorsSimulation.laws.size() == 1
    }
}
