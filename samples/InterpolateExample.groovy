laws {
    law 'occupancy' is interpolate(0..24) {
        x 6     y 10
        x 9     y 50
        x 10    y 40
        x 12    y 30
        x 14    y 25
        x 18    y 8
    }
}

simulation {
    lot ('Interpolate') {
        contains 1 sensors 'place' following 'occupancy' parameterized {
            during 1.year sampleEvery 1.minute

            period 3.hours
        }
    }
}