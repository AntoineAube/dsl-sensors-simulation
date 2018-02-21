package fr.polytech.dsl.dsl.model.structures.dashboards;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.structures.Lot;
import fr.polytech.dsl.dsl.model.structures.simulations.Simulation;

/**
 * Panel de Grafana
 *
 * @author Robin Alonzo
 */
public class Panel implements VisitableModel {

    private final String title;
    private Lot lot;
    private Simulation sensor;
    private int sensorNumber;
    private PanelType type;

    public Panel(String title) {
        this.title = title;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    public enum PanelType{
        GRAPH,
        TABLE
    }

    public String getTitle() {
        return title;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Simulation getSensor() {
        return sensor;
    }

    public void setSensor(Simulation sensor) {
        this.sensor = sensor;
    }

    public int getSensorNumber() {
        return sensorNumber;
    }

    public void setSensorNumber(int sensorNumber) {
        this.sensorNumber = sensorNumber;
    }

    public PanelType getType() {
        return type;
    }

    public void setType(PanelType type) {
        this.type = type;
    }

}
