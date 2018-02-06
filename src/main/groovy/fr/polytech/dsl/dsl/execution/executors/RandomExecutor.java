package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;

import java.util.List;
import java.util.Random;

public class RandomExecutor extends Executor {
    private List<Object> possibleValues;

    public RandomExecutor(Noise noise, float offset, float samplingPeriod, long dateFrom, long duration, String name, List<Object> possibleValues) {
        super(noise, offset, samplingPeriod, dateFrom, duration, name);
        this.possibleValues = possibleValues;
    }

    @Override
    Measure GetNext() {
        lastTimeGet += samplingPeriod;
        Random rand = new Random();
        return new Measure(dateFrom + lastTimeGet, possibleValues.get(rand.nextInt(possibleValues.size())), name);
    }
}
