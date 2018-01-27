package fr.polytech.dsl.dsl.validation;

import fr.polytech.dsl.dsl.model.VisitableModel;

public class InvalidModelException extends RuntimeException {

    private final transient VisitableModel faultyModelPart;

    public InvalidModelException(String message, VisitableModel faultyModelPart) {
        super(message);

        this.faultyModelPart = faultyModelPart;
    }

    public VisitableModel getFaultyModelPart() {
        return faultyModelPart;
    }
}
