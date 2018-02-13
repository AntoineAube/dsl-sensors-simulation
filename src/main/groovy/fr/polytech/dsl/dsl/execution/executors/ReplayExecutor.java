package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.execution.replays.CSVReplayReader;
import fr.polytech.dsl.dsl.execution.replays.JSONReplayReader;
import fr.polytech.dsl.dsl.execution.replays.ReplayReader;
import fr.polytech.dsl.dsl.model.structures.laws.ReplayLaw;
import fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;

public class ReplayExecutor extends Executor{

    private ReplayReader reader = null;

    public ReplayExecutor(String name,
                          long dateFrom,
                          long duration,
                          Noise noise,
                          double samplingPeriod,
                          String sourceFile,
                          ReplayLaw.ColumnsIndexes indexes,
                          String sensorName) throws IOException, ParseException {
        super(name, dateFrom, duration, noise, samplingPeriod);
        String extension = null;
        {
            int i = sourceFile.lastIndexOf('.');
            if (i > 0) {
                extension = sourceFile.substring(i+1);
            }
        }
        if(extension == null){
            return;
        }
        if(extension.toLowerCase().equals("csv")){
            reader = new CSVReplayReader(sourceFile,indexes,sensorName);
        }
        if(extension.toLowerCase().equals("json")){
            reader = new JSONReplayReader(sourceFile,indexes,sensorName);
        }
        //TODO JSON
    }

    @Override
    public Measure getNext() {
        if(reader != null){
            Measure measure = reader.readNext();
            if(measure != null){
                measure.setSensorName(this.name);
                measure.setTimestamp(measure.getTimestamp()+dateFrom);
                return measure;
            }
        }
        return null;
    }

    @Override
    public boolean hasFinished() {
        return reader == null || !reader.hasNext();
    }
}
