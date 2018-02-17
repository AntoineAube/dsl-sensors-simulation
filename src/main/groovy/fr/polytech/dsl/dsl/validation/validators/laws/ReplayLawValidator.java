package fr.polytech.dsl.dsl.validation.validators.laws;

import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;
import fr.polytech.dsl.dsl.validation.reporting.ValidationReport;
import fr.polytech.dsl.dsl.validation.validators.ModelValidator;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ReplayLawValidator extends LawValidator<ReplayLaw> {

    private static final List<String> AUTHORIZED_REPLAY_FORMAT = Arrays.asList("csv", "json");

    public ReplayLawValidator(ReplayLaw model, ValidationReport report) {
        super(model, report);
    }

    @Override
    public void runValidation() {
        // Check that the source has a correct format.
        checkSourceFormat();

        // Check that the source file exists.
        checkSourceExistence();

        // Check that the indexes are defined. Add a warning if some index are not of the same type..
        checkColumnsIndexes();

        // Check that the values type is Integer, Boolean or String.
        checkValuesType();

        // Check that the targeted sensor is not empty.
        checkTargetedSensor();
    }

    private void checkSourceFormat() {
        boolean hasValidSourceFormat = AUTHORIZED_REPLAY_FORMAT.stream()
                .anyMatch(extension -> model.getSourceFilePath().endsWith(extension));

        if (!hasValidSourceFormat) {
            ValidationReport.error(model)
                    .message("The source file path for the replay law '" + model.getName() + "' has not a valid extension.")
                    .save(report);
        }
    }

    private void checkSourceExistence() {
        File source = new File(model.getSourceFilePath());

        if (source.exists()) {
            if (source.isDirectory()) {
                ValidationReport.error(model)
                        .message("The source file for the replay law '" + model.getName() + "' is a directory.")
                        .save(report);
            }
        } else {
            ValidationReport.error(model)
                    .message("The source file for the replay law '" + model.getName() + "' does not exist.")
                    .save(report);
        }
    }

    private void checkColumnsIndexes() {
        ReplayLaw.ColumnsIndexes indexes = model.getIndexes();

        // Prepares the error reporting for undefined index.
        Consumer<String> undefinedIndexError = (indexType ->
                ValidationReport.error(model)
                        .message("The " + indexType + " index for the replay law '" + model.getName() + "' is not defined.")
                        .save(report));

        Runnable notHomogeneousIndexes = () ->
                ValidationReport.warning(model)
                        .message("The indexes for the replay law '" + model.getName() + "' are not homogeneous.")
                        .save(report);

        Class indexType = null;

        ReplayLaw.ColumnIndex timesIndex = indexes.getTimesIndex();
        if (timesIndex == null) {
            undefinedIndexError.accept("time");
        } else {
            indexType = timesIndex.getIndex().getClass();
        }

        ReplayLaw.ColumnIndex sensorsIndex = indexes.getSensorsIndex();
        if (sensorsIndex == null) {
            undefinedIndexError.accept("sensors");
        } else {
            if (indexType == null) {
                indexType = sensorsIndex.getIndex().getClass();
            } else if (indexType != sensorsIndex.getIndex().getClass()) {
                notHomogeneousIndexes.run();
                indexType = null;
            }
        }

        ReplayLaw.ColumnIndex valuesIndex = indexes.getSensorsIndex();
        if (valuesIndex == null) {
            undefinedIndexError.accept("values");
        } else {
            if (indexType != null && indexType != valuesIndex.getIndex().getClass()) {
                notHomogeneousIndexes.run();
            }
        }
    }

    private void checkTargetedSensor() {
        String targetedSensor = model.getTargetedSensor();
        if (targetedSensor == null || targetedSensor.isEmpty()) {
            ValidationReport.error(model)
                    .message("The replay law '" + model.getName() + "' targets no sensor.")
                    .save(report);
        }
    }
}
