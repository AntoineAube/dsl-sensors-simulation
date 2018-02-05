package fr.polytech.dsl.dsl.model.structures.simulations.modifications;

import java.util.List;

public class Noise {

    private final List<Integer> noiseValues;

    public Noise(List<Integer> noiseValues) {
        this.noiseValues = noiseValues;
    }

    public List<Integer> getNoiseValues() {
        return noiseValues;
    }
}
