package fr.polytech.dsl.dsl.model.structures.laws;

public abstract class TimeDependantLaw extends Law {

    private final double minimumTime;
    private final double maximumTime;

    public TimeDependantLaw(double minimumTime, double maximumTime) {
        this.minimumTime = minimumTime;
        this.maximumTime = maximumTime;
    }

    public double getMinimumTime() {
        return minimumTime;
    }

    public double getMaximumTime() {
        return maximumTime;
    }
}
