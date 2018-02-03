package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.model.Model;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

public abstract class Law implements Model {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Simulation createBlankSimulation();
}
