laws {
    law 'occupancy' is function(0..24) {
        returning Integer

        when 0..4 then {x}
        when 4..8 then {10}
        otherwise {x * 2}
    }
}

simulation {
    lot ('Function') {
        contains 1 sensors 'place' following 'occupancy' parameterized {
            during 1.year sampleEvery 1.minute

            period 3.hours
        }
    }
}