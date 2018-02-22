package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.Lot
import fr.polytech.dsl.dsl.model.structures.laws.Law
import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw
import groovy.time.TimeCategory

class LotScopeTest extends GroovyTestCase {

    private SensorSimulationBinding binding
    private Lot lot

    void setUp() {
        binding = new SensorSimulationBinding()

        Law law = new RandomLaw([1, 2, 3])
        law.name = 'a'

        binding.sensorsSimulation.laws.add(law)

        lot = new Lot('lotName')
    }

    private void executeContent(Closure content) {
        content.delegate = new LotScope(binding, lot)
        content.resolveStrategy = Closure.DELEGATE_FIRST

        use (TimeCategory) {
            content()
        }
    }

    void testEmptyLot() {
        executeContent { -> }

        assert lot.simulations.isEmpty()
    }

    void testOneSimpleSensor() {
        executeContent{ ->
            contains 1 sensor 's' following 'a' during 1.hour sampleEvery 1.minute
        }

        assert lot.simulations.size() == 1
    }

    void testOneComplexSensor() {
        executeContent {
            contains 2 sensors 's' following 'a' parameterized {

            }
        }

        assert lot.simulations.size() == 1
    }
}
