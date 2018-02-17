package fr.polytech.dsl.dsl.validation.validators.laws;

import fr.polytech.dsl.dsl.model.structures.laws.Law;
import fr.polytech.dsl.dsl.validation.reporting.ValidationReport;
import fr.polytech.dsl.dsl.validation.validators.ModelValidator;

import java.util.Arrays;
import java.util.List;

abstract class LawValidator<L extends Law> extends ModelValidator<L> {

    private static final List<Class> AUTHORIZED_VALUES_TYPES = Arrays.asList(String.class, Integer.class, Boolean.class);

    LawValidator(L model, ValidationReport report) {
        super(model, report);
    }

    void checkValuesType() {
        boolean valuesHaveAuthorizedType = AUTHORIZED_VALUES_TYPES.stream()
                .anyMatch(clazz -> clazz == model.getValuesType());

        if (!valuesHaveAuthorizedType) {
            ValidationReport.error(model)
                    .message("The selected column type for the law '" + model.getName() + "' is not authorized.");
        }
    }
}
