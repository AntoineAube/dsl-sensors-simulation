package fr.polytech.dsl.main;

import fr.polytech.dsl.dsl.SensorSimulationDSL;
import fr.polytech.dsl.dsl.execution.DatabaseConfiguration;
import fr.polytech.dsl.dsl.validation.ModelValidationException;
import org.apache.commons.cli.*;

import java.io.File;

public class Main {

    private static final String SCRIPT_FILE = "scriptFile";
    private static final String DATABASE_LOCATION = "databaseLocation";
    private static final String DATABASE_NAME = "databaseName";

    private static final String DEFAULT_DATABASE_NAME = "sensorsDatabase";
    private static final String DEFAULT_DATABASE_LOCATION = "http://localhost:8086";
    private static final String NO_EXECUTION = "noExecution";

    public static void main(String[] args) throws ParseException, ModelValidationException {
        CommandLine arguments = getArguments(args);

        DatabaseConfiguration configuration = new DatabaseConfiguration(DEFAULT_DATABASE_NAME, DEFAULT_DATABASE_LOCATION);

        if (arguments.hasOption(DATABASE_LOCATION)) {
            configuration.setDatabaseLocation(arguments.getOptionValue(DATABASE_LOCATION));
        }

        if (arguments.hasOption(DATABASE_NAME)) {
            configuration.setDatabaseName(arguments.getOptionValue(DATABASE_NAME));
        }

        SensorSimulationDSL dsl = new SensorSimulationDSL(configuration);

        dsl.evaluate(new File(arguments.getOptionValue(SCRIPT_FILE)), !arguments.hasOption(NO_EXECUTION));
    }

    private static CommandLine getArguments(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();

        Option scriptFileOption = Option.builder("s")
                .longOpt(SCRIPT_FILE)
                .desc("The path to the script file.")
                .required()
                .hasArg()
                .build();

        Option databaseLocationOption = Option.builder("l")
                .longOpt(DATABASE_LOCATION)
                .desc("The location of the database in which the measures should be generated.")
                .hasArg()
                .build();

        Option databaseNameOption = Option.builder("n")
                .longOpt(DATABASE_NAME)
                .desc("The name of the database in which the measure should be generated.")
                .hasArg()
                .build();

        Option noExecutionOption = Option.builder()
                .longOpt(NO_EXECUTION)
                .desc("Specify that the parsed model should not be executed")
                .hasArg(false)
                .build();

        options.addOption(scriptFileOption);
        options.addOption(databaseLocationOption);
        options.addOption(databaseNameOption);
        options.addOption(noExecutionOption);

        return parser.parse(options, args);
    }
}
