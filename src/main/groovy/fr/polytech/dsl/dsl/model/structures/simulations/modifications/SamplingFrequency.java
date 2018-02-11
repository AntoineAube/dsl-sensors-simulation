package fr.polytech.dsl.dsl.model.structures.simulations.modifications;

public class SamplingFrequency {

    private final double frequency;

    /**
     * Builds a sampling frequency.
     * @param frequency Frequency in hz.
     */
    public SamplingFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }
}
