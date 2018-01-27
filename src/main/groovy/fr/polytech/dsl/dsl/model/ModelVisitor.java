package fr.polytech.dsl.dsl.model;

import fr.polytech.dsl.dsl.model.structures.Configuration;
import fr.polytech.dsl.dsl.model.structures.Replay;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;

public interface ModelVisitor {

    void visit(Configuration configuration);
    void visit(SensorsSimulation sensorsSimulation);
    void visit(Replay replay);
}
