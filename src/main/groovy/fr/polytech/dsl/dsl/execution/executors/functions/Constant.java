package fr.polytech.dsl.dsl.execution.executors.functions;

public class Constant extends Expression{
    private double value;

    public Constant(double value){
        this.value = value;
    }

    @Override
    public double getValue(double variable) {
        return value;
    }
}
