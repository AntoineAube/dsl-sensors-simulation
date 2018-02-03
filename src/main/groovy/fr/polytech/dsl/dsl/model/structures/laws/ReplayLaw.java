package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.model.structures.simulations.ReplaySimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

public class ReplayLaw extends Law {

    private final String sourceFilePath;

    private String targetedSensor;
    private Class valuesType;

    private Object timesIndex;
    private Object valuesIndex;
    private Object sensorsIndex;

    public ReplayLaw(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public Class getValuesType() {
        return valuesType;
    }

    public void setValuesType(Class valuesType) {
        this.valuesType = valuesType;
    }

    public String getTargetedSensor() {
        return targetedSensor;
    }

    public void setTargetedSensor(String targetedSensor) {
        this.targetedSensor = targetedSensor;
    }

    public Object getTimesIndex() {
        return timesIndex;
    }

    public void setTimesIndex(Object timesIndex) {
        this.timesIndex = timesIndex;
    }

    public Object getValuesIndex() {
        return valuesIndex;
    }

    public void setValuesIndex(Object valuesIndex) {
        this.valuesIndex = valuesIndex;
    }

    public Object getSensorsIndex() {
        return sensorsIndex;
    }

    public void setSensorsIndex(Object sensorsIndex) {
        this.sensorsIndex = sensorsIndex;
    }

    @Override
    public Simulation createBlankSimulation() {
        return new ReplaySimulation(this);
    }
}
