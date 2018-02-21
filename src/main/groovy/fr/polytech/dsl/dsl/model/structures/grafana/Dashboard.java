package fr.polytech.dsl.dsl.model.structures.grafana;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dashboard de Grafana
 *
 * @author Robin Alonzo
 */
public class Dashboard {

    private String title;
    private List<Panel> panels = new ArrayList<>();
    private Date from;
    private Date to;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Panel> getPanels() {
        return panels;
    }

    public void setPanels(List<Panel> panels) {
        this.panels = panels;
    }

    public void addPanel(Panel panel){
        panels.add(panel);
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }


}
