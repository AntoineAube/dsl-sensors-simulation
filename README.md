# Sensors Simulations

*DSL course @ Polytech Nice-Sophia, 2017-2018.*

## Authors

* Robin Alonzo <alonzo.robin@etu.unice.fr>
* Antoine Aubé <aube.antoine@etu.unice.fr>
* Mathieu Mérino <merino.mathieu@etu.unice.fr>

## How to use

To start InfluxDB and Grafana, run ``docker-compose up``.

To compile the project, tun ``mvn package``.

Tu run a script, run ``mvn exec:java -Dexec.mainClass="fr.polytech.dsl.main.Main" -Dexec.args="-s <script.groovy> -k <Grafana API key>"``
