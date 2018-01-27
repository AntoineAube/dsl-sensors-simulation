package fr.polytech.dsl.dsl.syntax

import fr.polytech.dsl.dsl.syntax.ReplayScope.ChangeableValue

class ColumnCategory {

    static def isCase(Integer self, ChangeableValue value) {
        value.action(self)
    }

    static def isCase(String self, ChangeableValue value) {
        value.action(self)
    }
}
