laws {
    law 'parking place occupancy' is random('Available', 'Taken')
    law 'temperature law' is random(0..25)
}

simulation {
    lot ('Parking') {
        contains 20 sensors 'parking place' following 'parking place occupancy' during 2.hour sampleEvery 1.minute
        contains 10 sensors 'temperature' following 'temperature law' during 2.weeks sampleEvery 1.hour
    }
}