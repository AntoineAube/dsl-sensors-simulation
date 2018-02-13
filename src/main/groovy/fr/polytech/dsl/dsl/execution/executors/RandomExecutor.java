package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;

import java.util.List;
import java.util.Random;

public class RandomExecutor extends Executor {
    private List<Object> possibleValues;

    public RandomExecutor(String name, long dateFrom, long duration, Noise noise, double samplingPeriod, List<Object> possibleValues) {
        super(name, dateFrom, duration, noise, samplingPeriod);
        this.possibleValues = possibleValues;
    }

    @Override
    public Measure getNext() {
        Random rand = new Random();
        Measure measure = new Measure(lastTimeGet, possibleValues.get(rand.nextInt(possibleValues.size())), name);
        lastTimeGet += samplingPeriod;
        return measure;
    }

    @Override
    public boolean hasFinished(){
        return lastTimeGet >= dateFrom + duration;
    }
}
