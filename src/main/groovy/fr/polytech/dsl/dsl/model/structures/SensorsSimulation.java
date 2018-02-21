package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.structures.dashboards.Dashboard;
import fr.polytech.dsl.dsl.model.structures.laws.Law;

import java.util.ArrayList;
import java.util.List;

public class SensorsSimulation implements VisitableModel {

    private final List<Law> laws;
    private final SimulationContent simulation;
    private final Dashboard dashboard;

    public SensorsSimulation() {
        laws = new ArrayList<>();
        simulation = new SimulationContent();
        dashboard = new Dashboard();
    }

    public List<Law> getLaws() {
        return laws;
    }

    public SimulationContent getSimulation() {
        return simulation;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
