package fr.polytech.dsl.dsl.model;

import fr.polytech.dsl.dsl.model.structures.Lot;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;
import fr.polytech.dsl.dsl.model.structures.SimulationContent;
import fr.polytech.dsl.dsl.model.structures.laws.InterpolateLaw;
import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw;
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;
import fr.polytech.dsl.dsl.model.structures.laws.UnknownLaw;
import fr.polytech.dsl.dsl.model.structures.simulations.InterpolateSimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.RandomSimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.ReplaySimulation;
import fr.polytech.dsl.dsl.model.structures.simulations.UnknownSimulation;

public interface ModelVisitor {

    void visit(SensorsSimulation sensorsSimulation);
    void visit(SimulationContent simulationContent);
    void visit(Lot lot);

    void visit(RandomLaw randomLaw);
    void visit(ReplayLaw replayLaw);
    void visit(InterpolateLaw interpolateLaw);
    void visit(UnknownLaw unknownLaw);

    void visit(RandomSimulation randomSimulation);
    void visit(ReplaySimulation replaySimulation);
    void visit(InterpolateSimulation interpolateSimulation);
    void visit(UnknownSimulation unknownSimulation);
}
