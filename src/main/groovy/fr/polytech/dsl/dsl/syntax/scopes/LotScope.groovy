package fr.polytech.dsl.dsl.syntax.scopes

import fr.polytech.dsl.dsl.SensorSimulationBinding
import fr.polytech.dsl.dsl.model.structures.Lot
import fr.polytech.dsl.dsl.model.structures.laws.Law
import fr.polytech.dsl.dsl.model.structures.laws.UnknownLaw
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation
import fr.polytech.dsl.dsl.model.structures.simulations.UnknownSimulation
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.SamplingFrequency
import groovy.time.BaseDuration
import groovy.time.Duration
import groovy.time.TimeDuration

import java.text.SimpleDateFormat

class LotScope {

    private final SensorSimulationBinding binding
    private final Lot lot

    LotScope(SensorSimulationBinding binding, Lot lot) {
        this.binding = binding
        this.lot = lot
    }

    def contains(int sensorsNumber) {
        def sensorsClosure = {String sensorName ->
            [following: {String lawName ->
                Optional<Law> associatedLaw = binding.findLaw(lawName)

                Law unknown = new UnknownLaw()
                unknown.name = lawName
                Simulation simulation = unknown.createBlankSimulation()

                if (associatedLaw.isPresent()) {
                    simulation = associatedLaw.get().createBlankSimulation()
                    simulation.sensorName = sensorName

                    lot.addSimulations(simulation, sensorsNumber)
                } else {
                    lot.addSimulations(unknown.createBlankSimulation(), sensorsNumber)
                }

                [parameterized: {Closure simulationModifiers ->
                    simulationModifiers.delegate = simulation.createSimulationScope()
                    simulationModifiers.resolveStrategy = DELEGATE_FIRST

                    simulationModifiers()
                }, during: {BaseDuration duration ->
                    simulation.duration = duration.toMilliseconds()

                    def fromSpecification = [from: { String stringDate ->
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm")

                        Date fromDate = format.parse(stringDate)

                        simulation.dateFrom = fromDate.getTime()
                    }]

                    [at: {SamplingFrequency frequency ->
                        simulation.samplingFrequency = frequency

                        fromSpecification
                    },
                     sampleEvery: {BaseDuration period ->
                         simulation.samplingFrequency = new SamplingFrequency((double) 1000 / period.toMilliseconds())

                         fromSpecification
                     }]
                }]
            }]
        }
        [sensors: sensorsClosure, sensor: sensorsClosure]
    }
}
