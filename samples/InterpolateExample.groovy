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
            from "01/01/2017 00:00"
            during 1.week sampleEvery 1.minute
            noise 0..5
            period 24.hours
        }
    }
}

visualization {
    dashboard ("Interpolation"){
        from "01/01/2017 00:00"
        to "07/01/2017 00:00"

        graph("Occupancy"){
            lot 'Interpolate'
            sensor 'place'
            number 0
        }

    }
}
