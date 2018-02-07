import fr.polytech.dsl.dsl.model.structures.simulations.modifications.SamplingFrequency

laws {
    law 'temperature law' is replay('samples/day.csv') {
        fetch 'temperature' whose values are Integer

        times in 1
        values in 2
        sensors in 3
    }
}

simulation {
    lot ('School') {
        contains 3 sensors 'temperature' following 'temperature law' during 20.minutes at new SamplingFrequency((double) 1.0)
    }
}