package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.execution.executors.Executor;
import fr.polytech.dsl.dsl.execution.executors.functions.Expression;
import fr.polytech.dsl.dsl.model.structures.laws.FunctionLaw.FunctionCase;

import java.util.Set;
import java.util.function.Function;

public class FunctionExecutor extends Executor{
    private double max;
    private double offset;
    private double period;
    private double nextUnscaledVariable;
    private Set<FunctionCase> functions;
    private Function<Double, Object> otherwise;

    public FunctionExecutor(String name,
                            long dateFrom,
                            long duration,
                            fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise noise,
                            double samplingPeriod,
                            double min,
                            double max,
                            double period,
                            Set<FunctionCase> functions,
                            Function<Double, Object> otherwise){
        super(name, dateFrom, duration, noise, samplingPeriod);
        this.functions = functions;
        this.otherwise = otherwise;
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

        Function<Double, Object> function = null;
        for (FunctionCase f : functions){
            if (variable >= f.getMinimumTime() && variable < f.getMaximumTime()){
                function = f.getFunctionFragment();
            }
        }

        if (function == null){
            function = otherwise;
        }

        Object value = function.apply(variable);
        if (value instanceof Double){
            value = (Integer)((Double) value).intValue();
        }

        Measure measure = new Measure(lastTimeGet, value, name);

        nextUnscaledVariable += samplingPeriod;
        lastTimeGet += samplingPeriod;

        return measure;
    }

    @Override
    public boolean hasFinished() {
        return lastTimeGet >= dateFrom + duration;
    }
}
