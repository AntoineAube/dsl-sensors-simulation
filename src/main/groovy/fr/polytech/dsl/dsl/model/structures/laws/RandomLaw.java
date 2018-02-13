package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.simulations.RandomSimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

import java.util.List;

public class RandomLaw extends Law {

    private List<Object> possibleValues;

    public RandomLaw(List<Object> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public List<Object> getPossibleValues() {
        return possibleValues;
    }

    @Override
    public Class getValuesType() {
        return possibleValues.get(0).getClass();
    }

    @Override
    public Simulation createBlankSimulation() {
        return new RandomSimulation(this);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
