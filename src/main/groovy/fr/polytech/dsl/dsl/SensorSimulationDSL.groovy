package fr.polytech.dsl.dsl

import fr.polytech.dsl.dsl.validation.ModelValidationException
import fr.polytech.dsl.dsl.validation.SensorsSimulationValidator
import fr.polytech.dsl.dsl.validation.reporting.ValidationReport
import groovy.time.TimeCategory
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

class SensorSimulationDSL {

    private GroovyShell shell
    private SensorSimulationBinding binding

    SensorSimulationDSL() {
        binding = new SensorSimulationBinding()

        def configuration = getDSLConfiguration()
        configuration.setScriptBaseClass("fr.polytech.dsl.dsl.syntax.SensorSimulationScript")
        shell = new GroovyShell(configuration)
    }

    private static CompilerConfiguration getDSLConfiguration() {
        def secure = new SecureASTCustomizer()
        secure.with {
            // TODO Fill that when the concrete syntax is clearly defined.
        }

        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(secure)

        return configuration
    }

    void evaluate(File scriptFile) throws ModelValidationException {
        buildModel(scriptFile)

        validateModel()

       // executeModel()
    }

    void buildModel(File scriptFile) {
        Script script = shell.parse(scriptFile)

        script.setBinding(binding)

        use (TimeCategory) {
            script.run()
        }
    }

    void validateModel() throws ModelValidationException {
        SensorsSimulationValidator validator = new SensorsSimulationValidator()
        binding.sensorsSimulation.accept(validator)

        ValidationReport report = validator.getReport()

        displayReport(report)

        if (report.containsErrors()) {
            throw new ModelValidationException(report)
        }
    }

    void executeModel() {
      //  SensorsSimulationExecutor executor = new SensorsSimulationExecutor()

       // binding.sensorsSimulation.accept(executor)

       // executor.sendMeasures()
    }

    static void displayReport(ValidationReport report) {
        report.reportedEntries.forEach({ ValidationReport.Entry entry ->
            println(entry.status.toString() + "  :  " + entry.message)
        })
    }
}
