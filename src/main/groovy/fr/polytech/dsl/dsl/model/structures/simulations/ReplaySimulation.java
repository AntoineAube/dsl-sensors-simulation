package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.ReplaySimulationScope;
import fr.polytech.dsl.dsl.syntax.scopes.simulations.SimulationScope;

public class ReplaySimulation extends Simulation<ReplayLaw> {

    private long dateOffset;

    public ReplaySimulation(ReplayLaw associatedLaw) {
        super(associatedLaw);

        dateOffset = 0;
    }

    public long getDateOffset() {
        return dateOffset;
    }

    public void setDateOffset(long dateOffset) {
        this.dateOffset = dateOffset;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public SimulationScope createSimulationScope() {
        return new ReplaySimulationScope(this);
    }
}
