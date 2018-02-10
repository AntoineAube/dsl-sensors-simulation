package fr.polytech.dsl.dsl.execution.replays;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReplayReader extends ReplayReader {

    private static final CSVFormat CSV_FORMAT = CSVFormat.RFC4180.withFirstRecordAsHeader();

    private Iterator<CSVRecord> records;
    private ReplayLaw.ColumnsIndexes indexes;
    private String sensorName;

    public CSVReplayReader(String filePath, ReplayLaw.ColumnsIndexes indexes, String sensorName) throws IOException {
        super();
        FileReader reader = new FileReader(filePath);
        CSVParser parser = CSV_FORMAT.parse(reader);
        this.records = parser.iterator();
        this.indexes = indexes;
        this.sensorName = sensorName;
    }

    @Override
    public Measure readNext() {
        while (records.hasNext()){
            CSVRecord record = records.next();
            String sensor = record.get((Integer) indexes.getSensorsIndex().getIndex()-1);
            if (sensor.equals(sensorName)){
                long time = Long.parseLong(record.get((Integer) indexes.getTimesIndex().getIndex()-1));
                Object value = parseValue(record.get((Integer) indexes.getValuesIndex().getIndex()-1));
                return new Measure(time,value,sensorName);
            }
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        // peut renvoyer true meme si il ne reste aucune mesure pour le sensor souhaité
        return records.hasNext();
    }



    /*
    @Override
    List<Measure> readMeasures() throws IOException {
        Reader in = new FileReader(getReplay().getSourceLocation());

        List<Measure> measures = new ArrayList<>();
        for (CSVRecord record : CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in)) {
            long time = Long.parseLong(record.get(getReplay().getLocations().getTimesLocation() - 1));
            Object value = parseValue(record.get(getReplay().getLocations().getValuesLocation() - 1));
            String sensor = record.get(getReplay().getLocations().getSensorsLocation() - 1);

            measures.add(new Measure(time, value, sensor));
        }

        return measures;
    }*/
}
