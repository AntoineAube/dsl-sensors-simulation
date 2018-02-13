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
}

simulation {
    lot ('School') {
        contains 2 sensors 'temperature json' following 'temperature json' parameterized {
            during 1.year
            sampleEvery 1.day
            offset 17573.days
            noise 1..10
        }
        contains 3 sensors 'temperature csv' following 'temperature csv' parameterized {
            during 20.minutes
            sampleEvery 1.second
            offset 17573.days
        }
        contains 5 sensors 'parking place' following 'parking place occupancy' during 2.hours sampleEvery 1.minute
        contains 3 sensors 'temperature random' following 'temperature random' during 2.hours sampleEvery 2.minute
    }
}