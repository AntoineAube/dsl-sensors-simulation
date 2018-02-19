package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.execution.executors.Executor;
import fr.polytech.dsl.dsl.execution.executors.functions.Expression;

public class FunctionExecutor extends Executor{
    private Expression expression;
    private double max;
    private double offset;
    private double period;
    private double nextUnscaledVariable;

    public FunctionExecutor(String name,
                            long dateFrom,
                            long duration,
                            fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise noise,
                            double samplingPeriod,
                            double min,
                            double max,
                            double period,
                            Expression expression){
        super(name, dateFrom, duration, noise, samplingPeriod);
        this.expression = expression;
        this.max = max - min;
        this.offset = min;
        this.period = period;
        this.nextUnscaledVariable = 0;
    }

    @Override
    public Measure getNext() {
        nextUnscaledVariable %= period;
        double variable = (max * nextUnscaledVariable) / period;
        variable += offset;

        Measure measure = new Measure(lastTimeGet, expression.getValue(variable), name);

        nextUnscaledVariable += samplingPeriod;
        lastTimeGet += samplingPeriod;

        return measure;
    }

    @Override
    public boolean hasFinished() {
        return lastTimeGet >= dateFrom + duration;
    }
}
