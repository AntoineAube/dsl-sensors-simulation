import fr.polytech.dsl.dsl.model.structures.simulations.modifications.SamplingFrequency

laws {
    law 'temperature law' is replay('samples/day.json') {
        fetch 'temperature' whose values are Integer

        times in 't'
        values in 'v'
        sensors in 's'
    }
}

simulation {
    lot ('School') {
        contains 3 sensors 'temperature' following 'temperature law' parameterized {
            during 1.year at new SamplingFrequency((double) 1.0)

            offset 1.year
            noise 1..10
        }
    }
}