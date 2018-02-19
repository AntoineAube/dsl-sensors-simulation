package fr.polytech.dsl.dsl.execution.executors.functions;

public class Variable extends Expression{
    @Override
    public double getValue(double variable) {
        return variable;
    }
}
