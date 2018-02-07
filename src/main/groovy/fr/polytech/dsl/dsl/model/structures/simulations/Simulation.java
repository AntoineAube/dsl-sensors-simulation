package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.structures.laws.Law;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.SamplingFrequency;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.SimulationScope;

import java.util.Collections;

public abstract class Simulation<L extends Law> implements VisitableModel {

    private String sensorName;
    private final L associatedLaw;
    private SamplingFrequency samplingFrequency;
    private long duration;
    private long dateFrom;
    private Noise noise;

    public Simulation(L associatedLaw) {
        this.associatedLaw = associatedLaw;
        dateFrom = System.currentTimeMillis();
        duration = 0;
        noise = new Noise(Collections.emptyList());
    }

    public Noise getNoise() {
        return noise;
    }

    public void setNoise(Noise noise) {
        this.noise = noise;
    }

    public SamplingFrequency getSamplingFrequency() {
        return samplingFrequency;
    }

    public void setSamplingFrequency(SamplingFrequency samplingFrequency) {
        this.samplingFrequency = samplingFrequency;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public L getAssociatedLaw() {
        return associatedLaw;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public abstract SimulationScope createSimulationScope();
}
