package fr.polytech.dsl.dsl.model;

import fr.polytech.dsl.dsl.model.structures.Lot;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;
import fr.polytech.dsl.dsl.model.structures.SimulationContent;
import fr.polytech.dsl.dsl.model.structures.laws.*;
import fr.polytech.dsl.dsl.model.structures.simulations.*;

public interface ModelVisitor {

    void visit(SensorsSimulation sensorsSimulation);
    void visit(SimulationContent simulationContent);
    void visit(Lot lot);

    void visit(RandomLaw randomLaw);
    void visit(ReplayLaw replayLaw);
    void visit(InterpolateLaw interpolateLaw);
    void visit(FunctionLaw functionLaw);
    void visit(UnknownLaw unknownLaw);

    void visit(RandomSimulation randomSimulation);
    void visit(ReplaySimulation replaySimulation);
    void visit(InterpolateSimulation interpolateSimulation);
    void visit(FunctionSimulation functionSimulation);
    void visit(UnknownSimulation unknownSimulation);
}
