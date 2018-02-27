# Sensors Simulations

*DSL course @ Polytech Nice-Sophia, 2017-2018.*

## Authors

* Robin Alonzo <alonzo.robin@etu.unice.fr>
* Antoine Aubé <aube.antoine@etu.unice.fr>
* Mathieu Mérino <merino.mathieu@etu.unice.fr>

## How to use

To start InfluxDB and Grafana, run ``docker-compose up``. Grafana is then accessible at http://localhost:3000 with admin/admin.

To compile the project, run ``mvn package``.

Tu run a script, run ``mvn exec:java -Dexec.mainClass="fr.polytech.dsl.main.Main" -Dexec.args="-s <script.groovy> -k <Grafana API key>"``

To get an API key for Gafana, follow these [instructions](http://docs.grafana.org/http_api/auth/#create-api-token).

You can find samples in the "samples" directory.

## DSL

A script is composed of 3 parts:

#### The laws

```groovy
laws {
    law 'parking place occupancy' is random('Available', 'Taken')
    law 'temperature csv' is replay('samples/day.csv') {
        fetch 'temperature' whose values are Integer

        times in 1
        values in 2
        sensors in 3
    }
    law 'occupancy' is function(0..24) {
        returning Integer

        when 0..4 then {x+2}
        when 4..8 then {10}
        otherwise {x * 2}
    }
    law 'light' is interpolate(0..24) {
        x 6     y 10
        x 9     y 50
        x 10    y 40
        x 12    y 30
        x 14    y 25
        x 18    y 8
    }
}
```

These defines the datasources the sensors will simulate.

**Random** defines a list of values to pick at random. These values can be strings, bolleans, of integers, but these must all be from the same type.

**Replay** defines a replay from a .csv or .json file. Its parameters are :
- A file to replay from
- The name of the sensor in the replay file we want to simulate
- The type of values we are expected to get
- The indices for which the timestamps, the values and the sensor names are defined in the replay file. These indices are integers for csv files (column number starting from 1), and strings for json files (object attribute)

**Function** defines a function of x to follow. Its parameters are :
- An interval of values for x
- The return value type of the function (Integer, String, or Boolean)
- A list of functions defined by an interval for x, and a function of x
- An "otherwise" function used if x doesn't fit in the intervals defined in the list of function above

**Interpolate** defines an interpolation between points. Its parameters are :
- An interval of values for x
- A list of point (x,y) to interpolate

#### The simulation

```groovy
simulation {
    lot ('School') {
        contains 3 sensors 'temperature csv' following 'temperature csv' parameterized {
            during 20.minutes
            sampleEvery 1.second
            from "25/04/2017 15:00"
        }
        contains 5 sensors 'parking place' following 'parking place occupancy' during 2.hours sampleEvery 1.minute from "25/04/2017 15:00"
        contains 1 sensor 'occupancy' following 'occupancy' parameterized {
            from "25/04/2017 15:00"
            period 1.hour
            sampleEvery 10.minute
            during 1.day
            noise 0..5
        }
    }
}
```

A simulation is defined by a set of lots which correspond to different places the sensors are in, of just sets to keep things tidy.

In each lot, we can define a list of sensor to simulate. These are defined with :
- A number of sensors instances to simulate
- A name for the simulated sensors
- A law to follow
- A list of parameters defined in any order on the same line, of in a "parameterized" block :
    - **from** defines a date to start the simulation from
    - **offset** defines a offset of time for the generated measures (which has the same effect as **from** but is relative instead of being absolute)
    - **during** defines the lenght of the simulation in time
    - **sampleEvery** defines the frequency from which values are generated
    - **noise** defines an interval of integers to be added to the value at random (if the law return integers)
    - **period** defines the period of time it takes to get from the start to the end of the interval of values of x for the **interpolate** and **function** laws

#### The dashboards

```groovy
visualization {
    dashboard ("Dashboard name"){
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
        graph("Temperature"){
            lot 'School'
            sensor 'temperature csv'
            number 1
        }
    }
}
```

This part of the script is not mandatory. It defines dashboards to be displayed in Grafana.

A dashboards has a name, a start date, an end date, and a list of widgets.

Widgets can either be a table, or a graph. Both are defined with : 
- A name
- A lot name from which the sensor we want to display is
- The name of the sensor in the lot
- The number of the sensor 