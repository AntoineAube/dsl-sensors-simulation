package fr.polytech.dsl.dsl.syntax.scopes.laws

import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw

class ReplayScope {

    final def times
    final def values
    final def sensors

    private final ReplayLaw replay

    ReplayScope(ReplayLaw replay) {
        this.replay = replay

        times = new ChangeableValue({value -> replay.timesIndex = value})
        values = new ChangeableValue({value -> replay.valuesIndex = value})
        sensors = new ChangeableValue({value -> replay.sensorsIndex = value})
    }

    def fetch(String sensorName) {
        replay.targetedSensor = sensorName

        [whose: {whatever ->
            [are: {Class valuesType -> replay.valuesType = valuesType}]}]
    }

    static class ChangeableValue {

        final Closure action

        ChangeableValue(Closure action) {
            this.action = action
        }
    }
}
