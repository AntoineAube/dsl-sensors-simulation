lot ('Outside') {
    contains 10 sensors 'weather' following replay (csv: 'samples/weather.csv') {
        times in 1
        values in 2

        offset 2.years
    }

    contains 15 sensors 'climate' following replay (json: 'samples/climate.json') {
        times in 't'
        values in 'v'

        offset 6.months
    }
}