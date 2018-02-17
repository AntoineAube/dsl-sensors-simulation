package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.simulations.InterpolateSimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

import java.util.ArrayList;
import java.util.List;

public class InterpolateLaw extends TimeDependantLaw {

    private final List<Point> interpolatedPoints;

    public InterpolateLaw(double minimumTime, double maximumTime) {
        super(minimumTime, maximumTime);
        interpolatedPoints = new ArrayList<>();
    }

    @Override
    public Class getValuesType() {
        return Integer.class;
    }

    @Override
    public Simulation createBlankSimulation() {
        return new InterpolateSimulation(this);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    public List<Point> getInterpolatedPoints() {
        return interpolatedPoints;
    }

    public static class Point {
        private final double x;
        private final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return "(" + x + " ; " + y + ")";
        }
    }
}
