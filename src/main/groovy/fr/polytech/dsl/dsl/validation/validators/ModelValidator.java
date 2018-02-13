package fr.polytech.dsl.dsl.validation.validators;

import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.validation.reporting.ValidationReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ModelValidator<M extends VisitableModel> {

    protected final M model;
    protected final ValidationReport report;

    public ModelValidator(M model, ValidationReport report) {
        this.model = model;
        this.report = report;
    }

    protected void checkNamesValidity(String modelName, VisitableModel source, List<String> names) {
        Map<String, Integer> knownNames = new HashMap<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);

            if (name == null) {
                // A name cannot be null.
                ValidationReport.error(source)
                        .message("The " + i + ". " + modelName + " has a null name.")
                        .save(report);
            } else if (name.isEmpty()) {
                // A name cannot be empty.
                ValidationReport.error(source)
                        .message("The " + i + ". " + modelName + " has an empty name.")
                        .save(report);
            } else if (knownNames.containsKey(name)) {
                // A name cannot be duplicated for one same model type.
                ValidationReport.error(source)
                        .message("The " + i + ". " + modelName + " has the same name as the " + knownNames.get(name) + ". " + modelName + " ('" + name + "').")
                        .save(report);
            } else {
                knownNames.put(name, i);
            }
        }
    }

    public abstract void runValidation();
}
