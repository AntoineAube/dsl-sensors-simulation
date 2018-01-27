package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.ModelVisitor;

import java.util.ArrayList;
import java.util.List;

public class SensorsSimulation implements VisitableModel {

    private Configuration configuration;
    private final List<Replay> replays;

    public SensorsSimulation() {
        replays = new ArrayList<>();

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

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
