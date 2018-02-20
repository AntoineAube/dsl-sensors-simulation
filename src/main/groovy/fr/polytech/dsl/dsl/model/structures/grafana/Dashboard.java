package fr.polytech.dsl.dsl.model.structures.grafana;

import java.util.Date;
import java.util.List;

/**
 * Dashboard de Grafana
 *
 * @author Robin Alonzo
 */
public class Dashboard {
    private String title;
    private List<Panel> panels;
    private Date from;
    private Date to;
}
