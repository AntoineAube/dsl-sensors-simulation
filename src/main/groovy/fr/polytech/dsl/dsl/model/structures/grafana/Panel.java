package fr.polytech.dsl.dsl.model.structures.grafana;

/**
 * Panel de Grafana
 *
 * @author Robin Alonzo
 */
public class Panel {
    private String lot;
    private String sensor;
    private int sensorNumber;

    private PanelType type;

    public enum PanelType{
        GRAPH,
        TABLE
    }
}
