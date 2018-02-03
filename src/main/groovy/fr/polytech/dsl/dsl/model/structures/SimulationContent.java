package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.VisitableModel;

import java.util.ArrayList;
import java.util.List;

public class SimulationContent implements VisitableModel {

    private final List<Lot> lots;

    public SimulationContent() {
        lots = new ArrayList<>();
    }

    public List<Lot> getLots() {
        return lots;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
