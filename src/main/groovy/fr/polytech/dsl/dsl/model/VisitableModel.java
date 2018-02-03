package fr.polytech.dsl.dsl.model;

public interface VisitableModel {

    void accept(ModelVisitor visitor);
}
