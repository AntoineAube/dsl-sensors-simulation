import fr.polytech.dsl.dsl.model.structures.simulations.modifications.SamplingFrequency

laws {
    law 'parking place occupancy' is random('Available', 'Taken')
    law 'temperature law' is random(0..25)
}

simulation {
    lot ('Parking') {
        contains 20 sensors 'parking place' following 'parking place occupancy' during 2.hour at new SamplingFrequency((double) 1.0f/60.0f)
        contains 10 sensors 'temperature' following 'temperature law' during 2.weeks at new SamplingFrequency((double) 1.0f/3600.0f)
    }
}