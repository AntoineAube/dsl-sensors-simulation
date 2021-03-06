package fr.polytech.dsl.dsl.validation;

import fr.polytech.dsl.dsl.model.ModelVisitor;
import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.structures.Lot;
import fr.polytech.dsl.dsl.model.structures.SensorsSimulation;
import fr.polytech.dsl.dsl.model.structures.SimulationContent;
import fr.polytech.dsl.dsl.model.structures.dashboards.Dashboard;
import fr.polytech.dsl.dsl.model.structures.dashboards.Panel;
import fr.polytech.dsl.dsl.model.structures.laws.*;
import fr.polytech.dsl.dsl.model.structures.simulations.*;
import fr.polytech.dsl.dsl.validation.reporting.ValidationReport;
import fr.polytech.dsl.dsl.validation.validators.laws.FunctionLawValidator;
import fr.polytech.dsl.dsl.validation.validators.laws.InterpolateLawValidator;
import fr.polytech.dsl.dsl.validation.validators.laws.ReplayLawValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SensorsSimulationValidator implements ModelVisitor {

    private ValidationReport report;

    public SensorsSimulationValidator() {
        report = new ValidationReport();
    }

    public ValidationReport getReport() {
        return report;
    }

    @Override
    public void visit(SensorsSimulation sensorsSimulation) {
        List<Law> laws = sensorsSimulation.getLaws();

        // Check the laws names validity and uniqueness.
        checkNamesValidity("law", sensorsSimulation, laws.stream().map(Law::getName).collect(Collectors.toList()));

        laws.forEach(law -> law.accept(this));

        sensorsSimulation.getSimulation().accept(this);

        sensorsSimulation.getDashboards().forEach(dashboard -> dashboard.accept(this));
    }

    @Override
    public void visit(SimulationContent simulationContent) {
        List<Lot> lots = simulationContent.getLots();

        // Check the lots names validity and uniqueness.
        checkNamesValidity("lot", simulationContent, lots.stream().map(Lot::getName).collect(Collectors.toList()));

        lots.forEach(lot -> lot.accept(this));
    }


    @Override
    public void visit(Lot lot) {
        List<Simulation> simulations = lot.getSimulations().stream()
                .map(Lot.SimulationsBundle::getSimulation)
                .collect(Collectors.toList());

        // Check that each simulation has a valid and unique sensor name.
        checkNamesValidity("simulation", lot, simulations.stream().map(Simulation::getSensorName).collect(Collectors.toList()));

        lot.getSimulations().forEach(bundle -> {
            // Check that there is not a negative or null simulations number.
            if (bundle.getSimulationsNumber() < 1) {
                ValidationReport.error(lot)
                        .message("The sensor '" + bundle.getSimulation().getSensorName() + "' is declared but the amount of sensors is negative")
                        .save(report);
            }

            bundle.getSimulation().accept(this);
        });
    }

    @Override
    public void visit(RandomLaw randomLaw) {
        // Check that all possible values have the same type.
        List<Object> possibleValues = randomLaw.getPossibleValues();

        if (possibleValues.isEmpty()) {
            ValidationReport.error(randomLaw)
                    .message("The random law '" + randomLaw.getName() + "' has no possible values.")
                    .save(report);
        } else {
            Class listType = possibleValues.get(0).getClass();

            for (Object value : possibleValues) {
                if (value.getClass() != listType) {
                    ValidationReport.error(randomLaw)
                            .message("The random law '" + randomLaw.getName() + "' has not an homogeneous list of possible values.")
                            .save(report);
                }
            }
        }
    }

    @Override
    public void visit(ReplayLaw law) {
        new ReplayLawValidator(law, report).runValidation();
    }

    @Override
    public void visit(InterpolateLaw interpolateLaw) {
        new InterpolateLawValidator(interpolateLaw, report).runValidation();
    }

    @Override
    public void visit(FunctionLaw functionLaw) {
        new FunctionLawValidator(functionLaw, report).runValidation();
    }

    @Override
    public void visit(UnknownLaw unknownLaw) {
        ValidationReport.error(unknownLaw)
                .message("There is no known law named '" + unknownLaw.getName() + "'.")
                .save(report);
    }

    @Override
    public void visit(RandomSimulation randomSimulation) {
        // Nothing to check yet.
    }

    @Override
    public void visit(ReplaySimulation replaySimulation) {
        // Nothing to check yet.
    }

    @Override
    public void visit(InterpolateSimulation interpolateSimulation) {
        validateTimeDependantSimulation(interpolateSimulation);
    }

    @Override
    public void visit(FunctionSimulation functionSimulation) {
        validateTimeDependantSimulation(functionSimulation);
    }

    private void validateTimeDependantSimulation(TimeDependantSimulation simulation) {
        if (simulation.getLoopPeriod() == null) {
            ValidationReport.error(simulation)
                    .message("The loop period for simulation '" + simulation.getSensorName() + "' is not specified.")
                    .save(report);
        } else if (simulation.getLoopPeriod() == 0) {
            ValidationReport.error(simulation)
                    .message("The loop period for simulation '" + simulation.getSensorName() + "' should not be zero.")
                    .save(report);
        }
    }

    @Override
    public void visit(UnknownSimulation unknownSimulation) {
        unknownSimulation.getAssociatedLaw().accept(this);
    }

    @Override
    public void visit(Dashboard dashboard) {
        if (dashboard.getTitle() == null || dashboard.getTitle().equals("")) {
            ValidationReport.error(dashboard)
                    .message("Panel name is not specified.")
                    .save(report);
        }

        if (dashboard.getFrom() == null) {
            ValidationReport.error(dashboard)
                    .message("Panel from date is not specified.")
                    .save(report);
        }

        if (dashboard.getTo() == null) {
            ValidationReport.error(dashboard)
                    .message("Panel to date is not specified.")
                    .save(report);
        }

        dashboard.getPanels().forEach(panel -> panel.accept(this));
    }

    @Override
    public void visit(Panel panel) {
        if (panel.getSensor() == null) {
            ValidationReport.error(panel)
                    .message("Panel sensor is not set.")
                    .save(report);
        }

        if (panel.getLot() == null) {
            ValidationReport.error(panel)
                    .message("Panel lot is not set.")
                    .save(report);
        }

        if (panel.getSensorNumber() == null) {
            ValidationReport.error(panel)
                    .message("Panel sensor number is not set.")
                    .save(report);
        }
    }

    private void checkNamesValidity(String modelName, VisitableModel source, List<String> names) {
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
}
