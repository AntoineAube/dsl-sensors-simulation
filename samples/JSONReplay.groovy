laws {
    law 'temperature law' is replay('samples/day.json') {
        fetch 'temperature' whose values are Integer

        times in 't'
        values in 'v'
        sensors in 's'
    }
}

simulation {
    lot ('SchoolJSON') {
        contains 3 sensors 'temperature' following 'temperature law' parameterized {
            during 1.year sampleEvery 1.second

            offset 1.year
            noise 1..10
        }
    }
}