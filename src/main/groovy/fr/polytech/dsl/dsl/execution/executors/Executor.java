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

    public Executor(Noise noise, double samplingPeriod, long dateFrom, long duration, String name) {
        this.noise = noise;
        this.samplingPeriod = samplingPeriod;
        this.dateFrom = dateFrom;
        this.duration = duration;
        this.lastTimeGet = 0;
        this.name = name;
    }

    abstract Measure GetNext();

    boolean HasFinished(){
        return lastTimeGet > duration;
    }
}
