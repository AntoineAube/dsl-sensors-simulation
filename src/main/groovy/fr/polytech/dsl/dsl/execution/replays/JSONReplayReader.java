package fr.polytech.dsl.dsl.execution.replays;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JSONReplayReader extends ReplayReader {

    private ReplayLaw.ColumnsIndexes indexes;
    private String sensorName;
    private Iterator<JSONObject> objects;

    public JSONReplayReader(String sourceFile, ReplayLaw.ColumnsIndexes indexes, String sensorName) throws IOException, ParseException {
        this.sensorName = sensorName;
        this.indexes = indexes;
        JSONParser parser = new JSONParser();
        JSONArray document = (JSONArray) parser.parse(new FileReader(sourceFile));
        this.objects = document.iterator();
    }

    @Override
    public Measure readNext() {
        while(objects.hasNext()){
            JSONObject measureDocument = objects.next();
            String sensor = (String) measureDocument.get(indexes.getSensorsIndex().getIndex());
            if(sensorName.equals(sensor)) {
                Object value = measureDocument.get(indexes.getValuesIndex().getIndex());
                if(value instanceof Long){
                    value = (Integer) ((Long) value).intValue();
                }
                return new Measure(
                        (Long) measureDocument.get(indexes.getTimesIndex().getIndex()),
                        value,
                        sensor
                );
            }

        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return objects.hasNext();
    }

}
