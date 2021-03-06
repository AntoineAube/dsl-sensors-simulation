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
            from "25/04/2017 15:00"
            period 3.hours
        }
    }
}

visualization {
    dashboard ("Prout"){
        from "25/04/2017 15:00"
        to "25/04/2017 15:01"

        graph ("Croute2") {
            lot 'School'
            sensor 'place'
            number 2
        }
    }
}
