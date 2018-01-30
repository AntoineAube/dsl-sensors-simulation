package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.ModelVisitor;

import java.util.ArrayList;
import java.util.List;

public class SensorsSimulation implements VisitableModel {

    private Configuration configuration;
    private final List<Replay> replays;
    private final List<Sensor> sensors;

    public SensorsSimulation() {
        replays = new ArrayList<>();
        sensors = new ArrayList<>();

        configuration = new Configuration();
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<Replay> getReplays() {
        return replays;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
