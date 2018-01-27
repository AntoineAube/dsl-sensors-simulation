package fr.polytech.dsl.dsl.syntax

import fr.polytech.dsl.dsl.model.structures.Replay
import groovy.time.DatumDependentDuration
import groovy.time.TimeDuration
import groovy.transform.PackageScope

class ReplayScope {

    final def times
    final def values
    final def sensors
    final def lots

    private Replay replay

    @PackageScope
    ReplayScope(Replay replay) {
        times = new ChangeableValue({ value -> replay.locations.timesLocation = value })
        values = new ChangeableValue({ value -> replay.locations.valuesLocation = value })
        sensors = new ChangeableValue({ value -> replay.locations.sensorsLocation = value })
        lots = new ChangeableValue({ value -> replay.locations.lotsLocation = value })

        this.replay = replay
    }

    def offset(TimeDuration offsetValue) {
        replay.timestampOffset = offsetValue.toMilliseconds()
    }

    def offset(DatumDependentDuration offsetValue) {
        replay.timestampOffset = (long) offsetValue.toMilliseconds()
    }

    @PackageScope
    static class ChangeableValue {

        Closure action

        ChangeableValue(Closure action) {
            this.action = action
        }
    }
}
