package fr.polytech.dsl.integration;

import fr.polytech.dsl.dsl.SensorSimulationDSL;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;
import org.junit.Test;

import java.io.File;

/**
 * Extend this class to test the model parsing on a true script.
 */
public abstract class ModelParsingTest {

    private final File scriptFile;
    private SensorsSimulation model;

    ModelParsingTest(String fileLocation) {
        this.scriptFile = new File(fileLocation);
    }

    private SensorsSimulation getModel() {
        if (model == null) {
            SensorSimulationDSL dsl = new SensorSimulationDSL(null);
            dsl.buildModel(scriptFile);

            model = dsl.getModel();
        }

        return model;
    }

    @Test
    public void validateParsedModel() {
        validateParsedModel(getModel());
    }

    public abstract void validateParsedModel(SensorsSimulation model);
}
