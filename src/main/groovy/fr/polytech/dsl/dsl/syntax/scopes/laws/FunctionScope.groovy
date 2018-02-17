package fr.polytech.dsl.dsl.syntax.scopes.laws

import fr.polytech.dsl.dsl.model.structures.laws.FunctionLaw

import java.util.function.Function

class FunctionScope {

    private final FunctionLaw law

    FunctionScope(FunctionLaw law) {
        this.law = law
    }

    def returning(Class valuesType) {
        law.valuesType = valuesType
    }

    def when(List<Double> interval) {
        double min = interval[0]
        double max = interval[-1]

        [then: {Closure calculator ->
            Function<Double, Object> functionFragment = {x ->
                // This is maybe not thread-safe.
                calculator.setProperty('x', x)
                calculator.setProperty('t', x)

                return calculator()
            }

            law.functionFragments.add(new FunctionLaw.FunctionCase(min, max, functionFragment))
        }]
    }

    def otherwise(Closure calculator) {
        Function<Double, Object> functionFragment = {x ->
            // This is maybe not thread-safe.
            calculator.setProperty('x', x)
            calculator.setProperty('t', x)

            return calculator()
        }

        law.otherwiseFragment = functionFragment
    }
}
