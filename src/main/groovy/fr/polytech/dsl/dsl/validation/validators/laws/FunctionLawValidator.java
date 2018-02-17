package fr.polytech.dsl.dsl.validation.validators.laws;

import fr.polytech.dsl.dsl.model.structures.laws.FunctionLaw;
import fr.polytech.dsl.dsl.validation.reporting.ValidationReport;

import java.util.List;

public class FunctionLawValidator extends LawValidator<FunctionLaw> {

    public FunctionLawValidator(FunctionLaw model, ValidationReport report) {
        super(model, report);
    }

    @Override
    public void runValidation() {
        checkValuesType();

        if (checkIntervalCorrectness()) {
            checkIntervalCoverage();
            checkIntervalOverlaps();
        }
    }

    private boolean checkIntervalCorrectness() {
        boolean response = true;

        for (FunctionLaw.FunctionCase fragment : model.getFunctionFragments()) {
            if (fragment.getMinimumTime() > fragment.getMaximumTime()) {
                ValidationReport.error(model)
                        .message("In function law '" + model.getName() + "', there is a non well formed interval : (" +
                                fragment.getMinimumTime() + " ; " + fragment.getMaximumTime() + ").")
                        .save(report);

                response = false;
            }
        }

        return response;
    }

    private void checkIntervalOverlaps() {
        FunctionLaw.FunctionCase current = null;

        for (FunctionLaw.FunctionCase fragment : model.getFunctionFragments()) {
            // Initial case.
            if (current == null) {
                current = fragment;
                continue;
            }

            if (current.getMaximumTime() > fragment.getMinimumTime()) {
                ValidationReport.error(model)
                        .message("In function law '" + model.getName() + "', there is a overlap between " +
                                "(" + current.getMinimumTime() + " ; " + current.getMaximumTime() + ") and " +
                                "(" + fragment.getMinimumTime() + " ; " + fragment.getMaximumTime() + ").")
                        .save(report);
            }

            current = fragment;
        }
    }

    private void checkIntervalCoverage() {
        if (model.getOtherwiseFragment() == null && !isAllIntervalCovered()) {
            ValidationReport.error(model)
                    .message("The interval is not fully covered for the function law '" + model.getName() + "'.")
                    .save(report);
        }
    }

    /**
     * The intervals are supposed to be well formed.
     * @return True if all the "when" fragments cover the whole interval.
     */
    private boolean isAllIntervalCovered() {
        double current = model.getMinimumTime();

        for (FunctionLaw.FunctionCase fragment : model.getFunctionFragments()) {
            if (fragment.getMinimumTime() <= current) {
                current = fragment.getMaximumTime();
            } else {
                return false;
            }
        }

        return current >= model.getMaximumTime();
    }
}
