package fr.polytech.dsl.dsl.model.structures.simulations;

import fr.polytech.dsl.dsl.model.structures.laws.TimeDependantLaw;

public abstract class TimeDependantSimulation<L extends TimeDependantLaw> extends Simulation<L> {

    // Period in milliseconds.
    private Long loopPeriod;

    public TimeDependantSimulation(L associatedLaw) {
        super(associatedLaw);
    }

    public Long getLoopPeriod() {
        return loopPeriod;
    }

    public void setLoopPeriod(long loopPeriod) {
        this.loopPeriod = loopPeriod;
    }
}
