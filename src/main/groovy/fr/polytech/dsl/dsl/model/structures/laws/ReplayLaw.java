package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.simulations.ReplaySimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

public class ReplayLaw extends Law {

    private final String sourceFilePath;

    private String targetedSensor;
    private Class valuesType;

    private ColumnIndex timesIndex;
    private ColumnIndex valuesIndex;
    private ColumnIndex sensorsIndex;

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

    public ColumnIndex getTimesIndex() {
        return timesIndex;
    }

    public void setTimesIndex(Integer index) {
        timesIndex = new ColumnIndex(index);
    }

    public void setTimesIndex(String index) {
        timesIndex = new ColumnIndex(index);
    }

    public ColumnIndex getValuesIndex() {
        return valuesIndex;
    }

    public void setValuesIndex(Integer index) {
        valuesIndex = new ColumnIndex(index);
    }

    public void setValuesIndex(String index) {
        valuesIndex = new ColumnIndex(index);
    }

    public ColumnIndex getSensorsIndex() {
        return sensorsIndex;
    }

    public void setSensorsIndex(Integer index) {
        sensorsIndex = new ColumnIndex(index);
    }

    public void setSensorsIndex(String index) {
        sensorsIndex = new ColumnIndex(index);
    }

    @Override
    public Simulation createBlankSimulation() {
        return new ReplaySimulation(this);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    public static class ColumnIndex {

        private final Object index;

        private ColumnIndex(Integer index) {
            this.index = index;
        }

        private ColumnIndex(String index) {
            this.index = index;
        }

        public Object getIndex() {
            return index;
        }
    }
}
