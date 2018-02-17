laws {
    law 'occupancy' is interpolate(0..24) {
        x 6     y 0
        x 8     y 75
        x 9     y 100
        x 13    y 75
    }
}

simulation {
    lot ('School') {
        contains 12 sensors 'place' following 'occupancy' parameterized {
            during 1.year sampleEvery 1.second

            period 3.hours
        }
    }
}