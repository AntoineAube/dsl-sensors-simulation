package fr.polytech.dsl.dsl.model.structures.dashboards;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.VisitableModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dashboard de Grafana
 *
 * @author Robin Alonzo
 */
public class Dashboard implements VisitableModel {

    private String title;
    private final List<Panel> panels;
    private Date from;
    private Date to;

    public Dashboard() {
        panels = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Panel> getPanels() {
        return panels;
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

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
