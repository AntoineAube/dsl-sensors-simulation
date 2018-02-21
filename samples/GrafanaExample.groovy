laws {
    law 'occupancy' is function(0..24) {
        returning Integer

        when 0..1 then {x ** 2}
        when 1..5 then {1 / x}
        otherwise {x - 1}
    }
}

simulation {
    lot ('School') {
        contains 12 sensors 'place' following 'occupancy' parameterized {
            during 1.minute sampleEvery 1.second

            period 3.hours
        }
    }
}

visualization {
    dashboard ("Prout"){
        from "24/04/2017 15:01"
        to "25/04/2017 15:00"

        graph ("Croute") {
            lot 'School'
            sensor 'place'
            number 2
        }
    }
}
