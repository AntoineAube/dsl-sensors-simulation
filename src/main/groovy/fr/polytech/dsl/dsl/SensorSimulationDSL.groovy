package fr.polytech.dsl.dsl

import fr.polytech.dsl.dsl.execution.SensorsSimulationExecutor
import fr.polytech.dsl.dsl.validation.SensorSimulationValidator
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

    void evaluate(File scriptFile) {
        Script script = shell.parse(scriptFile)

        script.setBinding(binding)

        use (TimeCategory) {
            script.run()
        }

        validateModel()

        executeModel()
    }

    private void validateModel() {
        binding.sensorsSimulation.accept(new SensorSimulationValidator())
    }

    private void executeModel() {
        SensorsSimulationExecutor executor = new SensorsSimulationExecutor()

        binding.sensorsSimulation.accept(executor)

        executor.sendMeasures()
    }
}
