package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.simulations.FunctionSimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionLaw extends TimeDependantLaw {

    private Class valuesType;
    private final List<FunctionCase> functionFragments;
    private Function<Double, Object> otherwiseFragment;

    public FunctionLaw(double minimumTime, double maximumTime) {
        super(minimumTime, maximumTime);

        valuesType = Object.class;
        functionFragments = new ArrayList<>();
    }

    public List<FunctionCase> getFunctionFragments() {
        return functionFragments;
    }

    public Function<Double, Object> getOtherwiseFragment() {
        return otherwiseFragment;
    }

    public void setOtherwiseFragment(Function<Double, Object> otherwiseFragment) {
        this.otherwiseFragment = otherwiseFragment;
    }

    @Override
    public Class getValuesType() {
        return valuesType;
    }

    public void setValuesType(Class valuesType) {
        this.valuesType = valuesType;
    }

    @Override
    public Simulation createBlankSimulation() {
        return new FunctionSimulation(this);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    public static class FunctionCase {
        private double minimumTime;
        private double maximumTime;

        private Function<Double, Object> functionFragment;

        public FunctionCase(double minimumTime, double maximumTime, Function<Double, Object> functionFragment) {
            this.minimumTime = minimumTime;
            this.maximumTime = maximumTime;
            this.functionFragment = functionFragment;
        }

        public double getMinimumTime() {
            return minimumTime;
        }

        public double getMaximumTime() {
            return maximumTime;
        }

        public Function<Double, Object> getFunctionFragment() {
            return functionFragment;
        }
    }
}
