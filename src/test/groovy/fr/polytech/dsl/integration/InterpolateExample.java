package fr.polytech.dsl.integration;

import fr.polytech.dsl.dsl.model.structures.Lot;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;
import fr.polytech.dsl.dsl.model.structures.dashboards.Dashboard;
import fr.polytech.dsl.dsl.model.structures.laws.InterpolateLaw;
import fr.polytech.dsl.dsl.model.structures.simulations.InterpolateSimulation;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterpolateExample extends ModelParsingTest {

    InterpolateExample() {
        super("./samples/InterpolateExample.groovy");
    }

    @Override
    public void validateParsedModel(SensorsSimulation model) {
        // Check the law.
        assertEquals(1, model.getLaws().size());
        assertTrue(model.getLaws().get(0) instanceof InterpolateLaw);

        InterpolateLaw law = (InterpolateLaw) model.getLaws().get(0);

        assertEquals("occupancy", law.getName());
        assertEquals(Integer.class, law.getValuesType());
        assertEquals(0, (int) law.getMinimumTime());
        assertEquals(24, (int) law.getMaximumTime());

        List<InterpolateLaw.Point> points = law.getInterpolatedPoints();
        assertEquals(6, (int) points.get(0).getX());
        assertEquals(10, (int) points.get(0).getY());
        assertEquals(14, (int) points.get(3).getX());
        assertEquals(25, (int) points.get(3).getY());

        // Check the simulation.
        assertEquals(1, model.getSimulation().getLots().size());
        assertEquals(1, model.getSimulation().getLots().get(0).getSimulations().size());

        Lot.SimulationsBundle bundle = model.getSimulation().getLots().get(0).getSimulations().get(0);

        assertEquals(1, bundle.getSimulationsNumber());
        assertTrue(bundle.getSimulation() instanceof InterpolateSimulation);

        // Check the visualization.
        assertEquals(1, model.getDashboards().size());
        Dashboard dashboard = model.getDashboards().get(0);

        assertEquals("Interpolation", dashboard.getTitle());
        assertEquals(1, dashboard.getPanels().size());
    }
}
