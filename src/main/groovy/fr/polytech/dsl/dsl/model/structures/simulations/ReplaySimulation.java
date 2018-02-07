package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.ReplaySimulationScope;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.SimulationScope;

public class ReplaySimulation extends Simulation<ReplayLaw> {

    private Noise noise;

    public ReplaySimulation(ReplayLaw associatedLaw) {
        super(associatedLaw);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    public Noise getNoise() {
        return noise;
    }

    public void setNoise(Noise noise) {
        this.noise = noise;
    }

    @Override
    public SimulationScope createSimulationScope() {
        return new ReplaySimulationScope(this);
    }
}
