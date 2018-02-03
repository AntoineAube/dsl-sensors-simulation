package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.Model;
import fr.polytech.dsl.dsl.model.structures.laws.Law;

import java.util.ArrayList;
import java.util.List;

public class KnownLawsRegister implements Model {

    private final List<Law> laws;

    public KnownLawsRegister() {
        laws = new ArrayList<>();
    }

    public List<Law> getLaws() {
        return laws;
    }
}
