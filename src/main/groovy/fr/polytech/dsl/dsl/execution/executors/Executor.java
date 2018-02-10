package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;


public abstract class Executor {
    protected Noise noise;
    protected double samplingPeriod;
    protected long dateFrom;
    protected long duration;
    protected long lastTimeGet;
    protected String name;

    public Executor(String name, long dateFrom, long duration, Noise noise, double samplingPeriod) {
        this.noise = noise;
        this.samplingPeriod = samplingPeriod;
        this.dateFrom = dateFrom;
        this.duration = duration;
        this.lastTimeGet = dateFrom;
        this.name = name;
    }

    public abstract Measure getNext();

    public abstract boolean hasFinished();
}
