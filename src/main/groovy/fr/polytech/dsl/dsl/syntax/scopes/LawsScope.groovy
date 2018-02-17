package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.laws.*
import fr.polytech.dsl.dsl.syntax.scopes.categories.ReplayCategory
import fr.polytech.dsl.dsl.syntax.scopes.laws.FunctionScope
import fr.polytech.dsl.dsl.syntax.scopes.laws.InterpolateScope
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

    static Law interpolate(double min, double max, Closure interpolatedPoints) {
        InterpolateLaw law = new InterpolateLaw(min, max)

        interpolatedPoints.delegate = new InterpolateScope(law)
        interpolatedPoints.resolveStrategy = Closure.DELEGATE_FIRST

        interpolatedPoints()

        return law
    }

    static Law interpolate(List<Double> interval, Closure interpolatedPoints) {
        double min = interval[0]
        double max = interval[-1]

        return interpolate(min, max, interpolatedPoints)
    }

    static Law function(double min, double max, Closure functionParameters) {
        FunctionLaw law = new FunctionLaw(min, max)

        functionParameters.delegate = new FunctionScope(law)
        functionParameters.resolveStrategy = Closure.DELEGATE_FIRST

        functionParameters()

        return law
    }

    static Law function(List<Double> interval, Closure interpolatedPoints) {
        double min = interval[0]
        double max = interval[-1]

        return function(min, max, interpolatedPoints)
    }

    // Used a class instead of method chaining because "is" is a reserved operator.
    private class LawPreparation {

        private final String lawName

        LawPreparation(String lawName) {
            this.lawName = lawName
        }

        def is(Law law) {
            law.name = lawName

            binding.sensorsSimulation.laws.add(law)
        }
    }
}
