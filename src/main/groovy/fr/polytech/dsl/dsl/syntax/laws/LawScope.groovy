package fr.polytech.dsl.dsl.syntax.laws

import fr.polytech.dsl.dsl.model.structures.laws.Law

abstract class LawScope {

    protected final Law law

    LawScope(Law law) {
        this.law = law
    }
}
