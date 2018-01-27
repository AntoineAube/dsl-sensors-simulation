package fr.polytech.dsl.main;

import fr.polytech.dsl.dsl.SensorSimulationDSL;
import org.apache.commons.cli.*;

import java.io.File;

public class Main {

    private static final String SCRIPT_FILE = "scriptFile";

    public static void main(String[] args) throws ParseException {
        CommandLine arguments = getArguments(args);

        SensorSimulationDSL dsl = new SensorSimulationDSL();

        dsl.evaluate(new File(arguments.getOptionValue(SCRIPT_FILE)));
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

        options.addOption(scriptFileOption);

        return parser.parse(options, args);
    }
}
