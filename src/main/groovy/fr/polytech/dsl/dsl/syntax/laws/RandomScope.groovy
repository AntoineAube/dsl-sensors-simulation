package fr.polytech.dsl.dsl.syntax.laws

import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw

class RandomScope extends LawScope {

    RandomScope(RandomLaw law) {
        super(law)
    }

    def values(List values) {
        ((RandomLaw) law).choices = values
    }

    def values(Object... values) {
        ((RandomLaw) law).choices = Arrays.asList(values)
    }
}
