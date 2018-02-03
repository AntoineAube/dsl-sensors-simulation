package fr.polytech.dsl.dsl.syntax.scopes.categories

import fr.polytech.dsl.dsl.syntax.scopes.laws.ReplayScope

class ReplayCategory {

    static def isCase(Integer self, ReplayScope.ChangeableValue value) {
        value.action(self)
    }

    static def isCase(String self, ReplayScope.ChangeableValue value) {
        value.action(self)
    }
}
