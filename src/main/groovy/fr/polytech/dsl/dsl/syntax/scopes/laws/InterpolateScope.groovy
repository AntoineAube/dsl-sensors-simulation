package fr.polytech.dsl.dsl.syntax.scopes.laws

import fr.polytech.dsl.dsl.model.structures.laws.InterpolateLaw

class InterpolateScope {

    private final InterpolateLaw law

    InterpolateScope(InterpolateLaw law) {
        this.law = law
    }

    def x(double xValue) {
        [y: {double yValue -> law.interpolatedPoints.add(new InterpolateLaw.Point(xValue, yValue))}]
    }
}
