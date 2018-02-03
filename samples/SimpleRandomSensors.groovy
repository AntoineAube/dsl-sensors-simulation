laws {
    law 'parking place occupancy' is random('Available', 'Taken')
    law 'temperature law' is random(0..25)
}

simulation {
    lot ('Parking') {
        contains 20 sensors 'parking place' following 'parking place occupancy'
        contains 10 sensors 'temperature' following 'temperature law'
    }
}