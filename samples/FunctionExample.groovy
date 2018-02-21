laws {
    law 'occupancy' is function(0..24) {
        returning Integer

        when 0..4 then {x}
        when 4..8 then {10}
        otherwise {x * 2}
    }
}

simulation {
    lot ('Function') {
        contains 1 sensors 'place' following 'occupancy' parameterized {
            during 1.year sampleEvery 1.minute

            period 3.hours
        }
    }
}

display {
    dashboard ('DashboardName') {
        panel ('PanelName') is graph {
            description 'Description'
            draw bars
            draw lines {
                with staircase
                opacity 5
                width 5
            }
            draw points with radius 5
        }
        panel ('PanelName') is table {

        }
    }
}


Graph / Table