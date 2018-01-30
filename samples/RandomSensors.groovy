lot ('Parking') {
    contains 20 sensors 'parking place' following random {
        values 'Available', 'Taken'
    }

    contains 10 sensors 'temperature' following random {
        values 0..25
    }
}