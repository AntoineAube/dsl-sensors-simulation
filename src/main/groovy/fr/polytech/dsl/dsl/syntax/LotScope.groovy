package fr.polytech.dsl.dsl.syntax

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.Sensor
import fr.polytech.dsl.dsl.model.structures.laws.Law
import fr.polytech.dsl.dsl.model.structures.laws.RandomLaw

class LotScope {

    private SensorSimulationBinding binding
    private String lotName

    LotScope(SensorSimulationBinding binding, String lotName) {
        this.binding = binding
        this.lotName = lotName
    }

    private def addSensors(int sensorsNumber, String sensorName, Law followedLaw) {
        for (int i = 0; i < sensorsNumber; i++) {
            String name = lotName + "_" + sensorName + "_" + (i + 1)

            binding.getSensorsSimulation().sensors << new Sensor(name, followedLaw)
        }
    }

    def contains(int sensorsNumber) {
        Closure nextStep = {String sensorName -> [following: {Law followedLaw -> addSensors(sensorsNumber, sensorName, followedLaw)}]}

        [sensors: nextStep, sensor: nextStep]
    }

    private static Law buildLaw(Law law, Closure description) {
        description.delegate = law.getLawScope()
        description.resolveStrategy = Closure.DELEGATE_FIRST

        description()

        return law
    }

    static Law random(Closure description) {
        return buildLaw(new RandomLaw(), description)
    }
}
