package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.laws.Law
import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw
import fr.polytech.dsl.dsl.syntax.scopes.categories.ReplayCategory
import fr.polytech.dsl.dsl.syntax.scopes.laws.ReplayScope

class LawsScope {

    private final SensorSimulationBinding binding

    LawsScope(SensorSimulationBinding binding) {
        this.binding = binding
    }

    def law(String lawName) {
        return new LawPreparation(lawName)
    }

    static Law random(List<Object> possibleValues) {
        return new RandomLaw(possibleValues)
    }

    static Law random(Object... possibleValues) {
        return new RandomLaw(Arrays.asList(possibleValues))
    }

    static Law replay(String source, Closure replayParameters) {
        ReplayLaw law = new ReplayLaw(source)

        replayParameters.delegate = new ReplayScope(law)
        replayParameters.resolveStrategy = Closure.DELEGATE_FIRST

        use (ReplayCategory) {
            replayParameters()
        }

        return law
    }

    // Used a class instead of method chaining because "is" is a reserved operator.
    private class LawPreparation {

        private final String lawName

        LawPreparation(String lawName) {
            this.lawName = lawName
        }

        def is(Law law) {
            law.name = lawName

            binding.sensorsSimulation.laws.laws.add(law)
        }
    }
}
