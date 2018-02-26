package fr.polytech.dsl.integration;

import fr.polytech.dsl.dsl.model.structures.Lot;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;
import fr.polytech.dsl.dsl.model.structures.laws.FunctionLaw;
import fr.polytech.dsl.dsl.model.structures.simulations.FunctionSimulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FunctionExampleTest extends ModelParsingTest {

    public FunctionExampleTest() {
        super("./samples/FunctionExample.groovy");
    }

    @Override
    public void validateParsedModel(SensorsSimulation model) {
        // Check the law.
        assertEquals(1, model.getLaws().size());
        assertTrue(model.getLaws().get(0) instanceof FunctionLaw);

        FunctionLaw law = (FunctionLaw) model.getLaws().get(0);

        assertEquals("occupancy", law.getName());
        assertEquals(Integer.class, law.getValuesType());
        assertEquals(0, (int) law.getMinimumTime());
        assertEquals(24, (int) law.getMaximumTime());

        // TODO Should check the function fragments intervals.

        // Check the simulation.
        assertEquals(1, model.getSimulation().getLots().size());
        assertEquals(1, model.getSimulation().getLots().get(0).getSimulations().size());

        Lot.SimulationsBundle bundle = model.getSimulation().getLots().get(0).getSimulations().get(0);

        assertEquals(1, bundle.getSimulationsNumber());
        assertTrue(bundle.getSimulation() instanceof FunctionSimulation);

        FunctionSimulation simulation = (FunctionSimulation) bundle.getSimulation();
        assertEquals("place", simulation.getSensorName());
        assertEquals(law, simulation.getAssociatedLaw());
        assertEquals(604800000, simulation.getDuration()); // 1 week.
        assertEquals(1/60000, simulation.getSamplingFrequency().getFrequency(), 1);
        assertEquals(10800000, (long) simulation.getLoopPeriod()); // 3 hours.
    }
}
