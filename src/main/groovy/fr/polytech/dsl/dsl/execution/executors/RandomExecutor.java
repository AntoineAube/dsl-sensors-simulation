package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;

import java.util.List;
import java.util.Random;

public class RandomExecutor extends Executor {
    private List<Object> possibleValues;
    private Random rand = new Random();
    private Class valueType;

    public RandomExecutor(String name, long dateFrom, long duration, Noise noise, double samplingPeriod, List<Object> possibleValues, Class valueType) {
        super(name, dateFrom, duration, noise, samplingPeriod);
        this.possibleValues = possibleValues;
        this.valueType = valueType;
    }

    @Override
    public Measure getNext() {
        Measure measure = new Measure(lastTimeGet, possibleValues.get(rand.nextInt(possibleValues.size())), name);
        if(valueType == Integer.class && noise.getNoiseValues().size() > 0){
            measure.setValue((Integer) measure.getValue()+noise.getNoiseValues().get(rand.nextInt(noise.getNoiseValues().size())));
        }
        lastTimeGet += samplingPeriod;
        return measure;
    }

    @Override
    public boolean hasFinished(){
        return lastTimeGet >= dateFrom + duration;
    }
}
