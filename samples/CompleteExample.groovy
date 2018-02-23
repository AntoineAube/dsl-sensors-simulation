laws {
    law 'temperature json' is replay('samples/day.json') {
        fetch 'temperature' whose values are Integer

        times in 't'
        values in 'v'
        sensors in 's'
    }
    law 'temperature csv' is replay('samples/day.csv') {
        fetch 'temperature' whose values are Integer

        times in 1
        values in 2
        sensors in 3
    }
    law 'temperature random' is random(0..25)
    law 'parking place occupancy' is random('Available', 'Taken')
    law 'is day' is random(true, false)
    law 'occupancy' is function(0..24) {
        returning Integer

        when 0..4 then {x+2}
        when 4..8 then {10}
        otherwise {x * 2}
    }

}

simulation {
    lot ('School') {
        contains 2 sensors 'temperature json' following 'temperature json' parameterized {
            during 1.year
            sampleEvery 1.day
            from "25/04/2017 15:00"
            noise 100..200
        }
        contains 3 sensors 'temperature csv' following 'temperature csv' parameterized {
            during 20.minutes
            sampleEvery 1.second
            from "25/04/2017 15:00"
        }
        contains 5 sensors 'parking place' following 'parking place occupancy' during 2.hours sampleEvery 1.minute from "25/04/2017 15:00"
        contains 3 sensors 'temperature random' following 'temperature random' parameterized{
            from "25/04/2017 15:00"
            during 2.hours
            sampleEvery 2.minute
        }
        contains 1 sensor 'day' following 'is day' during 1.hour sampleEvery 1.minute from "25/04/2017 15:00"
        contains 1 sensor 'occupancy' following 'occupancy' parameterized {
            from "25/04/2017 15:00"
            period 1.hour
            sampleEvery 10.minute
            during 1.day
            noise (-2..3)
        }
    }
}

visualization {
    dashboard ("Complete Simulation"){
        from "25/04/2017 15:00"
        to "25/04/2017 16:00"

        table ("Place") {
            lot 'School'
            sensor 'parking place'
            number 2
        }
        graph ("Occupancy") {
            lot 'School'
            sensor 'occupancy'
            number 0
        }
        table ("Day") {
            lot 'School'
            sensor 'day'
            number 0
        }
        graph("Temperature"){
            lot 'School'
            sensor 'temperature csv'
            number 1
        }

    }
}
