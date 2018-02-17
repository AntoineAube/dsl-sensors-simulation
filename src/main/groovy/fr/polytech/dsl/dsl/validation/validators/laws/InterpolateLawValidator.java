package fr.polytech.dsl.dsl.validation.validators.laws;

import fr.polytech.dsl.dsl.model.structures.laws.InterpolateLaw;
import fr.polytech.dsl.dsl.validation.reporting.ValidationReport;

public class InterpolateLawValidator extends LawValidator<InterpolateLaw> {

    public InterpolateLawValidator(InterpolateLaw model, ValidationReport report) {
        super(model, report);
    }

    @Override
    public void runValidation() {
        checkValuesType();

        if (model.getInterpolatedPoints().isEmpty()) {
            ValidationReport.error(model)
                    .message("The interpolated law '" + model.getName() + "' has no point for interpolation.")
                    .save(report);
        }
    }
}
