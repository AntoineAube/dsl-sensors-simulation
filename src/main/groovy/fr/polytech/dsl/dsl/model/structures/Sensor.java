package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.structures.laws.Law;

public class Sensor implements VisitableModel {

    private final String name;
    private final Law followedLaw;

    public Sensor(String name, Law followedLaw) {
        this.name = name;
        this.followedLaw = followedLaw;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
