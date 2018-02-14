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
}

simulation {
    lot ('School') {
        contains 2 sensors 'temperature json' following 'temperature json' parameterized {
            during 1.year
            sampleEvery 1.day
            offset 17573.days
            noise 100..200
        }
        contains 3 sensors 'temperature csv' following 'temperature csv' parameterized {
            during 20.minutes
            sampleEvery 1.second
            offset 17573.days
        }
        contains 5 sensors 'parking place' following 'parking place occupancy' during 2.hours sampleEvery 1.minute
        contains 3 sensors 'temperature random' following 'temperature random' during 2.hours sampleEvery 2.minute
        contains 1 sensor 'day' following 'is day' during 1.hour sampleEvery 1.minute
    }
}