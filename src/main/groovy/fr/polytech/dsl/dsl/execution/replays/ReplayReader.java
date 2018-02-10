package fr.polytech.dsl.dsl.execution.replays;

import fr.polytech.dsl.dsl.execution.Measure;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public abstract class ReplayReader {

    private static final List<String> STRING_BOOLEANS = Arrays.asList("true", "false");
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^[\\-]?\\d+$");

    public abstract Measure readNext();
    public abstract boolean hasNext();

    static Object parseValue(String value) {
        if (STRING_BOOLEANS.contains(value.toLowerCase())) {
            return Boolean.parseBoolean(value.toLowerCase());
        }

        if (INTEGER_PATTERN.matcher(value).matches()) {
            return Integer.parseInt(value);
        }

        return value;
    }

    /*



    ReplayReader() {
    }

    abstract List<Measure> readMeasures() throws IOException;

    public List<Measure> readReplay() throws IOException {
        List<Measure> measures = readMeasures();
        measures.forEach(measure -> measure.setTimestamp(measure.getTimestamp() + replay.getTimestampOffset()));

        return measures;
    }

    Replay<L> getReplay() {
        return replay;
    }


    */
}
