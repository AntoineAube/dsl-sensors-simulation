package fr.polytech.dsl.dsl.execution.replays;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.model.structures.Replay;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReplayReader extends ReplayReader<Integer> {

    public CSVReplayReader(Replay<Integer> replay) {
        super(replay);
    }

    @Override
    List<Measure> readMeasures() throws IOException {
        Reader in = new FileReader(getReplay().getSourceLocation());

        List<Measure> measures = new ArrayList<>();
        for (CSVRecord record : CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in)) {
            long time = Long.parseLong(record.get(getReplay().getLocations().getTimesLocation() - 1));
            Object value = parseValue(record.get(getReplay().getLocations().getValuesLocation() - 1));
            String sensor = record.get(getReplay().getLocations().getSensorsLocation() - 1);
            String lot = record.get(getReplay().getLocations().getLotsLocation() - 1);

            measures.add(new Measure(time, value, sensor, lot));
        }

        return measures;
    }
}
