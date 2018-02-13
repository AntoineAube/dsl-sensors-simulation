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
import java.util.Random;

public class ReplayExecutor extends Executor{

    private ReplayReader reader = null;
    private Class valueType;
    private Random rand = new Random();

    public ReplayExecutor(String name,
                          long dateFrom,
                          long duration,
                          Noise noise,
                          double samplingPeriod,
                          String sourceFile,
                          ReplayLaw.ColumnsIndexes indexes,
                          String sensorName,
                          Class valueType) throws IOException, ParseException {
        super(name, dateFrom, duration, noise, samplingPeriod);
        this.valueType = valueType;
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
    }

    @Override
    public Measure getNext() {
        if(reader != null){
            Measure measure = reader.readNext();
            if(measure != null){
                measure.setSensorName(this.name);
                measure.setTimestamp(measure.getTimestamp()+dateFrom);
                if(valueType == Integer.class && noise.getNoiseValues().size() > 0){
                    measure.setValue((Integer) measure.getValue()+noise.getNoiseValues().get(rand.nextInt(noise.getNoiseValues().size())));
                }
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
