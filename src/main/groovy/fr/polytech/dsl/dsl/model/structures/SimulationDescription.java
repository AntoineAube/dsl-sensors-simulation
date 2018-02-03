package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.Model;

import java.util.ArrayList;
import java.util.List;

public class SimulationDescription implements Model {

    private final List<Lot> lots;

    public SimulationDescription() {
        lots = new ArrayList<>();
    }

    public List<Lot> getLots() {
        return lots;
    }
}
