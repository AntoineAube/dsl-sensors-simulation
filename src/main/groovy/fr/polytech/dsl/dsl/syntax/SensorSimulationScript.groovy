package fr.polytech.dsl.dsl.syntax

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.Replay

abstract class SensorSimulationScript extends Script {

    def replay(Map source, Closure replayDescription) {
        String sourceType = source.keySet()[0]
        String sourceLocation = source[sourceType]

        Replay newReplay
        if (sourceType == "csv") {
            newReplay = new Replay<Integer>(sourceType, sourceLocation)
        } else if (sourceType == "json") {
            newReplay = new Replay<String>(sourceType, sourceLocation)
        } else {
            throw new RuntimeException("Unknown source type for replay.")
        }

        ((SensorSimulationBinding) getBinding()).sensorsSimulation.replays << newReplay

        replayDescription.delegate = new ReplayScope(newReplay)
        replayDescription.resolveStrategy = Closure.DELEGATE_FIRST

        use (ColumnCategory) {
            replayDescription()
        }
    }

    def lot(String lotName, Closure lotDescription) {
        def lotScope = new LotScope(((SensorSimulationBinding) getBinding()), lotName)

        lotDescription.delegate = lotScope
        lotDescription.resolveStrategy = Closure.DELEGATE_FIRST

        lotDescription()
    }
}
