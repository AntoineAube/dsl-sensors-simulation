laws {
    law 'temperature law' is replay('sample/day.csv') {
        fetch 'temperature' whose values are Integer

        times in 1
        values in 2
        sensors in 3
    }
}

simulation {
    lot ('School') {
        contains 3 sensors 'temperature' following 'temperature law'
    }
}