package fr.polytech.dsl.dsl.validation;

import fr.polytech.dsl.dsl.validation.reporting.ValidationReport;

public class ModelValidationException extends Exception {

    private final transient ValidationReport report;

    public ModelValidationException(ValidationReport report) {
        super("The described model contains errors.");

        this.report = report;
    }

    public ValidationReport getReport() {
        return report;
    }
}
