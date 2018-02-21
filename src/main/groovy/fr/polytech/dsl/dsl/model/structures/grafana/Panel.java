package fr.polytech.dsl.dsl.model.structures.grafana;

/**
 * Panel de Grafana
 *
 * @author Robin Alonzo
 */
public class Panel {

    private String title;
    private String lot;
    private String sensor;
    private int sensorNumber;
    private PanelType type;

    public enum PanelType{
        GRAPH,
        TABLE
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
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
