import fr.polytech.dsl.dsl.model.structures.simulations.modifications.SamplingFrequency

laws {
    law 'parking place occupancy' is random('Available', 'Taken')
    law 'temperature law' is random(0..25)
}

simulation {
    lot ('Parking') {
        contains 20 sensors 'parking place' following 'parking place occupancy' during 1.hour at new SamplingFrequency((double) 1.0)
        contains 10 sensors 'temperature' following 'temperature law' during 2.years at new SamplingFrequency((double) 1.0)
    }
}