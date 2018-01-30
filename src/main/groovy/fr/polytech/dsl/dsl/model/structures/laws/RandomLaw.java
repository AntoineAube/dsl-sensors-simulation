package fr.polytech.dsl.dsl.model.structures.laws;

import fr.polytech.dsl.dsl.syntax.laws.RandomScope;

import java.util.ArrayList;
import java.util.List;

public class RandomLaw implements Law {

    private List<Object> choices;

    public RandomLaw() {
        this.choices = new ArrayList<>();
    }

    public List<Object> getChoices() {
        return choices;
    }

    public void setChoices(List<Object> choices) {
        this.choices = choices;
    }

    @Override
    public RandomScope getLawScope() {
        return new RandomScope(this);
    }
}
